package com.spmart.server.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryInfo {
	private Long id;
	private String name;
	private CategoryInfo parent;
	private List<CategoryInfo> children;
}
