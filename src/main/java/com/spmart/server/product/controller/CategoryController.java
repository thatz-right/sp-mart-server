package com.spmart.server.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spmart.server.common.dto.StatusMessage;
import com.spmart.server.common.exception.StatusCode;
import com.spmart.server.product.dto.CategoryInfo;
import com.spmart.server.product.dto.CategoryRequest;
import com.spmart.server.product.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryInfo>> getCategoryList() {
		List<CategoryInfo> categoryInfoList = categoryService.getListTopNode();

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(categoryInfoList);
	}

	@PostMapping
	public ResponseEntity<StatusMessage> create(@RequestBody CategoryRequest categoryRequest) {
		categoryService.save(categoryRequest);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(new StatusMessage(StatusCode.SUCCESS));
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<StatusMessage> update(@PathVariable Long categoryId,
		@RequestBody CategoryRequest categoryRequest) {
		categoryService.update(categoryId, categoryRequest);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(new StatusMessage(StatusCode.SUCCESS));
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<StatusMessage> remove(@PathVariable Long categoryId) {
		categoryService.remove(categoryId);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(new StatusMessage(StatusCode.SUCCESS));
	}

}
