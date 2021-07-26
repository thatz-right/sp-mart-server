package com.spmart.server.product.dto;

import java.util.List;

import com.spmart.server.product.domain.CategoryItem;
import com.spmart.server.product.domain.Product;
import com.spmart.server.product.domain.ProductOption;

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
public class ProductRequest {
	private Long id;
	private String code;
	private String name;
	private String description;
	private String image;
	private Long categoryItemId;
	private boolean isDisplay;
	private boolean isStock;
	private List<OptionInfo> options;
	private int discountPrice;
	private int price;

	public Product toEntity() {
		Product product = Product.builder()
			.id(id)
			.code(code)
			.name(name)
			.description(description)
			.image(image)
			.categoryItem(CategoryItem.builder().id(categoryItemId).build())
			.isDisplay(isDisplay).isStock(isStock).discountPrice(discountPrice).price(price).build();

		for (OptionInfo optionInfo : options) {
			product.addProductOption(optionInfo.toEntity());
		}

		return product;
	}
}
