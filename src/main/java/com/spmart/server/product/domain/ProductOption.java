package com.spmart.server.product.domain;

import com.spmart.server.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ProductOption extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OptionValue> values = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	public void addOptionValue(OptionValue optionValue) {
		values.add(optionValue);
		optionValue.setProductOption(this);
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Builder
	public ProductOption(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
