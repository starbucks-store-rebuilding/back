package com.starbucks.back.review.dto.out;

import com.starbucks.back.review.vo.out.ResponseReviewSummaryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ResponseReviewSummaryDto {

    private Double averageRating;
    private Long totalReviews;

    public ResponseReviewSummaryDto(Double averageRating, Long totalReviews) {
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

    public ResponseReviewSummaryVo toVo() {
        return ResponseReviewSummaryVo.builder()
                .averageRating(averageRating)
                .totalReviews(totalReviews)
                .build();
    }

}
