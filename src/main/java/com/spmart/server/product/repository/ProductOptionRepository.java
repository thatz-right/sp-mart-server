package com.spmart.server.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spmart.server.product.domain.ProductOption;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
	Optional<ProductOption> findByProductId(Long productId);
}
