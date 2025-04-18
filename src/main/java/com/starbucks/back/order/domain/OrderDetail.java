package com.starbucks.back.order.domain;

import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "order_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_list_uuid", nullable = false, length = 50)
    private String orderListUuid;

    @Column(name = "order_detail_uuid", nullable = false, length = 50)
    private String orderDetailUuid;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "thumbnail", nullable = false, length = 255)
    private String thumbnail;

    @Column(name = "price", nullable = false)
    private Integer totalOriginPrice;

    @Column(name = "discount_rate", nullable = false)
    private Integer discountRate;

    @Column(name = "discount_price", nullable = false)
    private Integer totalPurchasePrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Builder
    public OrderDetail(
            String orderListUuid,
            String orderDetailUuid,
            String name,
            String thumbnail,
            Integer totalOriginPrice,
            Integer discountRate,
            Integer totalPurchasePrice,
            Integer quantity
    ) {
        this.orderListUuid = orderListUuid;
        this.orderDetailUuid = orderDetailUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.totalOriginPrice = totalOriginPrice;
        this.discountRate = discountRate;
        this.totalPurchasePrice = totalPurchasePrice;
        this.quantity = quantity;
    }

}
