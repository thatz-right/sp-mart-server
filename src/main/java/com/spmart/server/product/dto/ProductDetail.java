package com.spmart.server.product.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetail {
	private Long id;
	private String code;
	private String name;
	private String description;
	private String image;
	private CategoryInfo categoryInfo;
	private boolean isStock;
	private List<OptionInfo> options;
	private int discountPrice;
	private int price;
}
