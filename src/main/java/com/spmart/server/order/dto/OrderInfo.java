package com.spmart.server.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.spmart.server.auth.dto.UserInfo;
import com.spmart.server.order.domain.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderInfo {
	private Long id;
	private UserInfo user;
	private List<OrderProductInfo> orderProducts;
	private LocalDateTime createdDate;
	private OrderStatus orderStatus;
	private int totalPrice;
}
