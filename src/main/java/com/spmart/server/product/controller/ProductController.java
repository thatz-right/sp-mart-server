package com.spmart.server.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.product.service.ProductService;

import lombok.RequiredArgsConstructor;


@RestController("/products")
@RequiredArgsConstructor
public class ProductController {

//	private final ProductService productService;

	@GetMapping("/{categoryId}")
	public ResponseEntity<PageResponse> getProductList(@PathVariable Long categoryId, @RequestParam int pageNum) {
//		return new ResponseEntity<>(productService.getProductCardList(categoryId, pageNum));

		return null;
	}

}
