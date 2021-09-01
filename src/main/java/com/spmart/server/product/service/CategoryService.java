package com.spmart.server.product.service;

import com.spmart.server.product.dto.CategoryRequest;

public interface CategoryService {

    //카테고리 저장
    void save(CategoryRequest categoryRequest);

    //카테고리 삭제
    void remove(Long categoryId);

    // 카테고리 수정
    void update(CategoryRequest categoryRequest);
}

