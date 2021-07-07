package com.spmart.server.product.domain;

import com.spmart.server.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ProductOption extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany
	@Column
	private List<OptionValue> values;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Builder
	public ProductOption(Long id, String name, List<OptionValue> values) {
		this.id = id;
		this.name = name;
		this.values = values;
	}
}
