package com.spmart.server.product.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.spmart.server.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Product extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String name;

	private String description;

	private String image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_item_id")
	private CategoryItem categoryItem;

	private boolean isDisplay;

	private boolean isStock;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductOption> options;

	private int discountPrice;

	private int price;

	@Builder
	public Product(Long id, String code, String name, String description, String image, CategoryItem categoryItem, boolean isDisplay,
		boolean isStock, List<ProductOption> options, int discountPrice, int price) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.image = image;
		this.categoryItem = categoryItem;
		this.isDisplay = isDisplay;
		this.isStock = isStock;
		this.options = options != null ? options : new ArrayList<>();
		this.discountPrice = discountPrice;
		this.price = price;
	}
}
