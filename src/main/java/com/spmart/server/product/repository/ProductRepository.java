package com.spmart.server.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spmart.server.product.domain.Product;
import com.spmart.server.product.dto.ProductCard;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
}
