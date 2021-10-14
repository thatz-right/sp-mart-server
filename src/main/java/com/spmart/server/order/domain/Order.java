package com.spmart.server.order.domain;

import com.spmart.server.auth.domain.User;
import com.spmart.server.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderProduct> orderProducts = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	public int getTotalPrice() {
		return orderProducts.stream().mapToInt(item -> item.getTotalPrice()).sum();
	}

	public void addOrderProduct(OrderProduct orderProduct) {
		orderProducts.add(orderProduct);
		orderProduct.setOrder(this);
	}

	public void setOrderStatus(OrderStatus status) {
		this.orderStatus = status;
	}

	@Builder
	public Order(Long id, User user, OrderStatus orderStatus) {
		this.id = id;
		this.user = user;
		this.orderStatus = orderStatus != null ? orderStatus : OrderStatus.PAYED;
	}
}
