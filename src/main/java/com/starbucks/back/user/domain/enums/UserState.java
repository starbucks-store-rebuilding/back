package com.starbucks.back.user.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserState {
    DELETED(0, "탈퇴"),
    PENDING_DELETION(1, "탈퇴예정"),
    ACTIVE(2, "활성화");

    private final int code;
    @JsonValue  // json으로 변환 시 해당 값을 사용
    private final String label;

    UserState(int code, String label) {
        this.code = code;
        this.label = label;
    }

//    @JsonCreator  /* 입력받은 값에 해당하는 UserState를 찾아 반환하는 메소드 */
//    public static UserState fromCode(int code) {
//        return Arrays.stream(UserState.values())
//                .filter(state -> state.code == code)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Invalid UserState code: " + code));
//    }
}
