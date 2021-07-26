package com.spmart.server.product.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

	@Column(unique = true)
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
	private List<ProductOption> options = new ArrayList<>();

	private int discountPrice;

	private int price;

	public void addProductOption(ProductOption productOption) {
		options.add(productOption);
		productOption.setProduct(this);
	}

	@Builder
	public Product(Long id, String code, String name, String description, String image, CategoryItem categoryItem,
		boolean isDisplay,
		boolean isStock, int discountPrice, int price) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.image = image;
		this.categoryItem = categoryItem;
		this.isDisplay = isDisplay;
		this.isStock = isStock;
		this.discountPrice = discountPrice;
		this.price = price;
	}

	public void update(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.description = product.getDescription();
		this.image = product.getImage();
		this.categoryItem = product.getCategoryItem();

		this.options.clear();
		this.options.addAll(product.getOptions());

		this.isDisplay = product.isDisplay();
		this.isStock = product.isStock();
		this.discountPrice = product.getDiscountPrice();
		this.price = product.getPrice();
	}
}
