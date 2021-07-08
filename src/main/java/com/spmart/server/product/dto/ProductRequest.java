package com.spmart.server.product.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	private Long id;
	private String code;
	private String name;
	private String description;
	private String image;
	private Long categoryItemId;
	private boolean isDisplay;
	private boolean isStock;
	private List<OptionRequest> options;
	private int discountPrice;
	private int price;
}
