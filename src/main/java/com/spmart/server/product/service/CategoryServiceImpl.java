package com.spmart.server.product.service;

import com.spmart.server.product.domain.Category;
import com.spmart.server.product.dto.CategoryInfo;
import com.spmart.server.product.dto.CategoryRequest;
import com.spmart.server.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryInfo> getListTopNode(Long parentId) {
        return null;
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
