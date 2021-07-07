package com.spmart.server.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spmart.server.product.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findAllByOrderById();
}
