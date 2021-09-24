package com.spmart.server.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.spmart.server.product.domain.Category;
import com.spmart.server.product.dto.CategoryInfo;
import com.spmart.server.product.dto.CategoryRequest;
import com.spmart.server.product.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<CategoryInfo> getListTopNode() {

		return categoryRepository.findAllByParentIdIsNull()
			.stream()
			.map(category -> modelMapper.map(category, CategoryInfo.class))
			.collect(Collectors.toList());
	}

	@Override
	public void save(CategoryRequest categoryRequest) {
		Category category = categoryRequest.toEntity();
		categoryRepository.save(category);
	}

	@Override
	public void remove(Long categoryId) {
		categoryRepository.delete(Category.builder().id(categoryId).build());
	}

	@Override
	public void update(CategoryRequest categoryRequest) {
		Category category = categoryRepository
			.findById(categoryRequest.getId())
			.orElseThrow(IllegalArgumentException::new);

		category.update(category);

		categoryRepository.save(category);
	}
}
