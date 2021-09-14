//package com.spmart.server.product.controller;
//
//import com.spmart.server.common.dto.PageResponse;
//import com.spmart.server.common.dto.StatusMessage;
//import com.spmart.server.common.exception.StatusCode;
//import com.spmart.server.product.dto.ProductCard;
//import com.spmart.server.product.dto.ProductDetail;
//import com.spmart.server.product.dto.ProductRequest;
//import com.spmart.server.product.service.CategoryService;
//import com.spmart.server.product.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController("/api/v1/category")
//@RequiredArgsConstructor
//public class CategoryController {
//
//    private final CategoryService categoryService;
//    /*
//    //카테고리 저장
//    void save(CategoryRequest categoryRequest);
//
//    //카테고리 삭제
//    void remove(Long categoryId);
//
//    // 카테고리 수정
//    void update(CategoryRequest categoryRequest);
//     */
//    @GetMapping
//    public ResponseEntity<PageResponse<ProductCard>> getProductList(
//            @RequestParam(required = false) Long categoryId,
//            @RequestParam(required = false, defaultValue = "1") int pageNum) {
//        PageResponse<ProductCard> pageResponse = productService.getListByCategory(categoryId, pageNum);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(pageResponse);
//    }
//
//    @GetMapping("/{productId}")
//    public ResponseEntity<ProductDetail> getDetail(@PathVariable Long productId) {
//        ProductDetail productDetail = productService.getDetail(productId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(productDetail);
//    }
//
//    @PostMapping
//    public ResponseEntity<StatusMessage> create(@RequestBody ProductRequest productRequest) {
//        productService.save(productRequest);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new StatusMessage(StatusCode.SUCCESS));
//    }
//
//    @PutMapping("/{productId}")
//    public ResponseEntity<StatusMessage> update(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
//        productService.update(productRequest);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new StatusMessage(StatusCode.SUCCESS));
//    }
//
//    @DeleteMapping("/{productId}")
//    public ResponseEntity<StatusMessage> remove(@PathVariable Long productId) {
//        productService.remove(productId);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new StatusMessage(StatusCode.SUCCESS));
//    }
//
//}
