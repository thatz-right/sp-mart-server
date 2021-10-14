package com.spmart.server.order.dto;

import java.time.LocalDate;

import com.spmart.server.product.dto.ProductCard;

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
public class OrderProductInfo {
	private int count;
	private String title;
	private ProductCard product;
	private LocalDate receivedDate;
	private String receiver;
	private String gifter;
	private String content;
	private int productPrice;
}
