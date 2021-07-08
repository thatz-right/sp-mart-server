package com.spmart.server.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController("/products")
public class ProductController {

	private final ProductService productService;

	@GetMapping("/{categoryId}")
	public ResponseEntity<PageResponse> getProductList(@PathVariable Long categoryId) {
		// this.productService.selectAll();

		return null;
	}
}
