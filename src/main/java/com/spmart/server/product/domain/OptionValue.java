package com.spmart.server.product.domain;

import com.spmart.server.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class OptionValue extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int extraPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_option_id")
	private ProductOption productOption;

	public void setProductOption(ProductOption productOption) {
		this.productOption = productOption;
	}

	@Builder
	public OptionValue(Long id, String name, int extraPrice) {
		this.id = id;
		this.name = name;
		this.extraPrice = extraPrice;
	}
}
