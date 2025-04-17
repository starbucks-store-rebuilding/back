package com.starbucks.back.review.dto.in;

import com.starbucks.back.review.domain.Review;
import com.starbucks.back.review.vo.in.RequestAddReviewVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;

@Getter
@NoArgsConstructor
public class RequestAddReviewDto {

    private String userUuid;
    private String productUuid;
    private String title;
    private String content;
    private Integer rating;

    @Builder
    public RequestAddReviewDto(String userUuid, String productUuid, String title, String content, Integer rating) {
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public Review toEntity() {
        return Review.builder()
                .reviewUuid(randomUUID().toString())
                .userUuid(userUuid)
                .productUuid(productUuid)
                .title(title)
                .content(content)
                .rating(rating)
                .build();
    }

    public static RequestAddReviewDto of(String userUuid, RequestAddReviewVo requestAddReviewVo) {
        return RequestAddReviewDto.builder()
                .userUuid(userUuid)
                .productUuid(requestAddReviewVo.getProductUuid())
                .title(requestAddReviewVo.getTitle())
                .content(requestAddReviewVo.getContent())
                .rating(requestAddReviewVo.getRating())
                .build();
    }

}
