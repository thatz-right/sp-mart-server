package com.spmart.server.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spmart.server.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class CategoryItem extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Builder
	public CategoryItem(Long id, String name, Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}
}
