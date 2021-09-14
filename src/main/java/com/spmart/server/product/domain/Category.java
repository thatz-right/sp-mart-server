package com.spmart.server.product.domain;

import com.spmart.server.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Category extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name="parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> children = new ArrayList<>();

	@Builder
	public Category(Long id, String name, Category parent) {
		this.id = id;
		this.name = name;
		this.parent = parent;
	}

	public void update(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.parent = category.getParent();
	}

	public void addParent(Category parent) {
		this.parent = parent;
		parent.getChildren().add(this);
	}
}
