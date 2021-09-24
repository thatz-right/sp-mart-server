package com.spmart.server.product.service;

import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.product.domain.Category;
import com.spmart.server.product.domain.OptionValue;
import com.spmart.server.product.domain.Product;
import com.spmart.server.product.domain.ProductOption;
import com.spmart.server.product.dto.CategoryRequest;
import com.spmart.server.product.dto.ProductCard;
import com.spmart.server.product.dto.ProductDetail;
import com.spmart.server.product.dto.ProductRequest;
import com.spmart.server.product.repository.CategoryRepository;
import com.spmart.server.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*
        //카테고리 저장
        void save(CategoryInfo categoryInfo);
        //카테고리 삭제
        void remove(Long categoryId);
        // 카테고리 수정
        void update(CategoryInfo categoryInfo);
     */
    @Test
    @DisplayName("카테고리 저장")
    public void save() {
        Category category = Category.builder()
                .id(1L)
                .name("부모 카테고리")
                .build();


        CategoryRequest categoryRequest = modelMapper.map(category, CategoryRequest.class);

        when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(category);

        categoryService.save(categoryRequest);

        verify(categoryRepository).save(Mockito.any(Category.class));
    }

    @Test
    @DisplayName("카테고리 수정")
    public void update() {
        Category category = Category.builder()
                .id(1L)
                .name("부모 카테고리")
                .build();

        CategoryRequest categoryRequest = modelMapper.map(category, CategoryRequest.class);

        when(categoryRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(category));

        when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(Mockito.any(Category.class));

        categoryService.update(1L, categoryRequest);

        verify(categoryRepository).save(Mockito.any(Category.class));
        verify(categoryRepository).findById(Mockito.anyLong());
    }

    @Test
    @DisplayName("카테고리 삭제")
    public void remove() {
		doNothing().when(categoryRepository).delete(Mockito.any(Category.class));

        categoryService.remove(Mockito.anyLong());

        verify(categoryRepository).delete(Mockito.any(Category.class));
    }
}
