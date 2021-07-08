package com.spmart.server.product.service;

import org.springframework.stereotype.Service;

import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.product.dto.ProductCard;
import com.spmart.server.product.dto.ProductDetail;
import com.spmart.server.product.dto.ProductRequest;

public interface ProductService {
	PageResponse<ProductCard> findProductCardListByCategoryId(); // 상품정보 리스트, pageable;

	ProductDetail findProductDetail(Long productId);

	boolean registProduct(ProductRequest productRequest);

	boolean updateProduct(ProductRequest productRequest);

	boolean removeProduct(Long productId);
}
