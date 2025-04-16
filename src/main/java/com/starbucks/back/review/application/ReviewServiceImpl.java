package com.starbucks.back.review.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.review.domain.Review;
import com.starbucks.back.review.dto.in.RequestAddReviewDto;
import com.starbucks.back.review.dto.in.RequestDeleteReviewDto;
import com.starbucks.back.review.dto.in.RequestUpdateReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewSummaryDto;
import com.starbucks.back.review.infrastructure.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * 리뷰 등록
     * @param requestAddReviewDto
     */
    @Transactional
    @Override
    public void addReview(RequestAddReviewDto requestAddReviewDto) {
        reviewRepository.save(requestAddReviewDto.toEntity());
    }

    /**
     * 유저 uuid로 리뷰 조회
     * @param userUuid
     */
    @Override
    public List<ResponseReviewDto> getReviewsByUserUuid(String userUuid) {
        return reviewRepository.findByUserUuidAndDeletedFalse(userUuid)
                .stream()
                .map(ResponseReviewDto::from)
                .toList();
    }

    /**
     * 리뷰 uuid로 리뷰 조회
     * @param reviewUuid
     */
    @Override
    public ResponseReviewDto getReviewByReviewUuid(String reviewUuid) {
        Review review = reviewRepository.findByReviewUuidAndDeletedFalse(reviewUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEW));
        return ResponseReviewDto.from(review);
    }

    /**
     * 상품 uuid로 리뷰 조회
     * @param productUuid
     */
    @Override
    public List<ResponseReviewDto> getReviewByProductUuid(String productUuid) {
        return reviewRepository.findByProductUuidAndDeletedFalse(productUuid)
                .stream()
                .map(ResponseReviewDto::from)
                .toList();
    }

    /**
     * 상품 리뷰 평균 평점, 개수 조회
     * @param productUuid
     */
    @Override
    public ResponseReviewSummaryDto getReviewSummaryByProductUuid(String productUuid) {
        return reviewRepository.findReviewSummaryByProductUuidAndDeletedFalse(productUuid);
    }

    /**
     * 리뷰 전체 조회
     */
    @Override
    public List<ResponseReviewDto> getAllReviews() {
        return reviewRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseReviewDto::from)
                .toList();
    }

    /**
     * 리뷰 수정
     * @param requestUpdateReviewDto
     */
    @Transactional
    @Override
    public void updateReview(RequestUpdateReviewDto requestUpdateReviewDto) {
        Review review = reviewRepository.findByReviewUuidAndDeletedFalse(requestUpdateReviewDto.getReviewUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEW));
        reviewRepository.save(requestUpdateReviewDto.updateEntity(review));
    }

    /**
     * 리뷰 삭제
     * @param requestDeleteReviewDto
     */
    @Transactional
    @Override
    public void deleteReview(RequestDeleteReviewDto requestDeleteReviewDto) {
        Review review = reviewRepository.findByReviewUuidAndDeletedFalse(requestDeleteReviewDto.getReviewUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEW));
        review.softDelete();
    }

}
