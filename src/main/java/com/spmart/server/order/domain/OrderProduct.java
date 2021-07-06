package com.spmart.server.order.domain;

import com.spmart.server.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    @Min(value = 0)
    private int count;

    @Builder
    public OrderProduct(Product product, Order order, int count) {
        this.product = product;
        this.order = order;
        this.count = count;
        this.orderPrice = product.getDiscountPrice() * count;
    }
}
