package com.spmart.server.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCard {
	private Long id;
	private String name;
	private String code;
	private String image;
	private int discountPrice;
}
