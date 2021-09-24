package com.spmart.server.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.common.dto.StatusMessage;
import com.spmart.server.common.exception.StatusCode;
import com.spmart.server.product.dto.ProductCard;
import com.spmart.server.product.dto.ProductDetail;
import com.spmart.server.product.dto.ProductRequest;
import com.spmart.server.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse<ProductCard>> getProductList(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false, defaultValue = "1") int pageNum) {
        PageResponse<ProductCard> pageResponse = productService.getListByCategory(categoryId, pageNum);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pageResponse);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetail> getDetail(@PathVariable Long productId) {
        ProductDetail productDetail = productService.getDetail(productId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productDetail);
    }

    @PostMapping
    public ResponseEntity<StatusMessage> create(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StatusMessage(StatusCode.SUCCESS));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<StatusMessage> update(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        productService.update(productId, productRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StatusMessage(StatusCode.SUCCESS));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<StatusMessage> remove(@PathVariable Long productId) {
        productService.remove(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StatusMessage(StatusCode.SUCCESS));
    }

}
