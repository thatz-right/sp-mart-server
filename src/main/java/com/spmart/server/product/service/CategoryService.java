package com.spmart.server.product.service;

import com.spmart.server.product.dto.CategoryInfo;
import com.spmart.server.product.dto.CategoryRequest;

import java.util.List;

public interface CategoryService {

    //최상위 카테고리 리스트 조회
    List<CategoryInfo> getListTopNode();

    //카테고리 저장
    void save(CategoryRequest categoryRequest);

    //카테고리 삭제
    void remove(Long categoryId);

    // 카테고리 수정
    void update(Long categoryId, CategoryRequest categoryRequest);
}

