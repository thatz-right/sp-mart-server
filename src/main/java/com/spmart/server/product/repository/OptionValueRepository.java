package com.spmart.server.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spmart.server.product.domain.OptionValue;

public interface OptionValueRepository extends JpaRepository<OptionValue, Long> {
	List<OptionValue> findAllByProductOptionId(Long productOptionId);
}
