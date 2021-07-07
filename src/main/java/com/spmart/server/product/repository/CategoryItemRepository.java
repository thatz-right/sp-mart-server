package com.spmart.server.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spmart.server.product.domain.Category;
import com.spmart.server.product.domain.CategoryItem;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
	List<CategoryItem> findAllByCategoryId(Long categoryId);
}
