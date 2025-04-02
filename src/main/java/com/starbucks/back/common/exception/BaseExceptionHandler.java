package com.starbucks.back.common.exception;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    /**
     * 발생한 예외 처리
     */

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> BaseError(BaseException e) {
        BaseResponseEntity<Void> response = new BaseResponseEntity<>(e.getStatus());
        log.error("BaseException -> {}({})", e.getStatus(), e.getStatus().getMessage(), e);
        return new ResponseEntity<>(response, response.httpStatus());
    }

    /**
     * security 인증 에러
     * 아이디가 없거나 비밀번호가 틀린 경우 AuthenticationManager 에서 발생
     *
     * @return FAILED_TO_LOGIN 에러 response
     */
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> handleBadCredentialsException(BadCredentialsException e) {
        BaseResponseEntity<Void> response = new BaseResponseEntity<>(BaseResponseStatus.FAILED_TO_LOGIN);
        log.error("BadCredentialsException: ", e);
        return new ResponseEntity<>(response, response.httpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> RuntimeError(RuntimeException e) {
        BaseResponseEntity<Void> response = new BaseResponseEntity<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        log.error("RuntimeException: ", e);
        for (StackTraceElement s : e.getStackTrace()) {
            System.out.println(s);
        }
        return new ResponseEntity<>(response, response.httpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        FieldError fieldError = bindingResult.getFieldError();
        String errorMessage = (fieldError != null)
                ? String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage())
                : "잘못된 요청입니다.";

        log.warn("Validation failed: {}", errorMessage);

        BaseResponseEntity<Void> response = new BaseResponseEntity<>(
                BaseResponseStatus.INVALID_REQUEST.getHttpStatusCode(),
                false,
                errorMessage,
                BaseResponseStatus.INVALID_REQUEST.getCode(),
                null
        );
        return new ResponseEntity<>(response, response.httpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> handleJsonParseException(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();

        if (cause != null && cause.getMessage() != null && cause.getMessage().contains("java.time.LocalDate")) {
            String errorMessage = "생년월일은 yyyy-mm-dd 형식이어야 합니다.";
            log.warn("LocalDate parsing failed: {}", cause.getMessage());
            return new ResponseEntity<>(
                    new BaseResponseEntity<>(
                            BaseResponseStatus.INVALID_REQUEST.getHttpStatusCode(),
                            false,
                            errorMessage,
                            BaseResponseStatus.INVALID_REQUEST.getCode(),
                            null
                    ),
                    BaseResponseStatus.INVALID_REQUEST.getHttpStatusCode()
            );
        }

        return new ResponseEntity<>(
                new BaseResponseEntity<>(
                        BaseResponseStatus.INVALID_REQUEST.getHttpStatusCode(),
                        false,
                        "요청 형식이 올바르지 않습니다.",
                        BaseResponseStatus.INVALID_REQUEST.getCode(),
                        null
                ),
                BaseResponseStatus.INVALID_REQUEST.getHttpStatusCode()
        );
    }

}
