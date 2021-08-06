package com.spmart.server.product.service;

import org.springframework.stereotype.Service;

import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.product.dto.ProductCard;
import com.spmart.server.product.dto.ProductDetail;
import com.spmart.server.product.dto.ProductRequest;

@Service
public interface ProductService {
	PageResponse<ProductCard> getProductCardList(Long categoryId, int pageNum);

	ProductDetail getProductDetail(Long productId);

	void registProduct(ProductRequest productRequest);

	void updateProduct(ProductRequest productRequest);

	void removeProduct(Long productId);
}
