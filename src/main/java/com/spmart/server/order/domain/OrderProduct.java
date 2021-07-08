package com.spmart.server.order.domain;

import java.time.LocalDate;

import com.spmart.server.common.BaseTimeEntity;
import com.spmart.server.product.domain.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Entity
public class OrderProduct extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int orderPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@Min(value = 0)
	private int count;

	private String title;

	private LocalDate date;

	private String receiver;

	private String gifter;

	private String content;

	@Builder
	public OrderProduct(Long id, int orderPrice, Product product, Order order, int count, String title,
		LocalDate date, String receiver, String gifter, String content) {
		this.id = id;
		this.orderPrice = orderPrice;
		this.product = product;
		this.order = order;
		this.count = count;
		this.title = title;
		this.date = date;
		this.receiver = receiver;
		this.gifter = gifter;
		this.content = content;
	}
}
