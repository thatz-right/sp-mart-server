package com.spmart.server.product.service;

import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.product.dto.ProductCard;
import com.spmart.server.product.dto.ProductDetail;
import com.spmart.server.product.dto.ProductRequest;

public interface ProductService {

    PageResponse<ProductCard> getListByCategory(Long categoryId, int pageNum);

    ProductDetail getDetail(Long productId);

    void save(ProductRequest productRequest);

    void update(ProductRequest productRequest);

    void remove(Long productId);
}
