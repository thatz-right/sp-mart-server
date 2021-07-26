package com.spmart.server.product.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.spmart.server.product.domain.CategoryItem;
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
public class ProductDetail {
	private Long id;
	private String code;
	private String name;
	private String description;
	private String image;
	private CategoryItemInfo categoryItem;
	private boolean isStock;
	private List<OptionInfo> options;
	private int discountPrice;
	private int price;
}
