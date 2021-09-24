package com.spmart.server.product.repository;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.spmart.server.product.domain.Category;

@DataJpaTest
@ActiveProfiles("test")
public class CategoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 아이템 추가하기")
    public void createCategory() {
        Category parent = Category.builder().name("금속").build();

        Category saved = categoryRepository.save(parent);

        Category item1 = Category.builder().parent(parent).name("동 상패").build();
        Category item2 = Category.builder().parent(parent).name("은 상패").build();
        Category item3 = Category.builder().parent(parent).name("금 상패").build();

        item1.addParent(parent);
        item2.addParent(parent);
        item3.addParent(parent);

        categoryRepository.saveAll(Arrays.asList(item1, item2, item3));

        Category category = categoryRepository.findById(parent.getId()).orElseThrow();

        Assertions.assertThat(category.getChildren().size()).isEqualTo(3);

        category.getChildren().forEach(item -> {
            System.out.println(item.getName());
        });
    }

    @Test
    @DisplayName("카테고리 id로 세부목록 받아오기")
    public void selectByCategoryId() {

        Category category = categoryRepository.save(Category.builder().name("금속").build());

        Category item1 = Category.builder().parent(category).name("동 상패").build();
        Category item2 = Category.builder().parent(category).name("은 상패").build();
        Category item3 = Category.builder().parent(category).name("금 상패").build();

        item1.addParent(category);
        item2.addParent(category);
        item3.addParent(category);

        categoryRepository.saveAll(Arrays.asList(item1, item2, item3));

        Category findCategory = categoryRepository.findById(category.getId()).orElseThrow();

        Assertions.assertThat(findCategory.getChildren().size()).isEqualTo(3);
        findCategory.getChildren().forEach(categoryItem -> System.out.println(categoryItem.getName()));
    }

    @Test
    @DisplayName("최상단 카테고리 리스트 조회")
    public void selectAll() {
        Category category = categoryRepository.save(Category.builder().name("금속").build());

        Category item1 = Category.builder().name("동 상패").build();
        Category item2 = Category.builder().name("은 상패").build();
        Category item3 = Category.builder().name("금 상패").build();
        item1.addParent(category);
        item2.addParent(category);
        item3.addParent(category);

        categoryRepository.saveAll(Arrays.asList(item1, item2, item3));

        Category item1_1 = Category.builder().name("동동 상패").build();
        item1_1.addParent(item1);
        categoryRepository.saveAndFlush(item1_1);

        List<Category> list = categoryRepository.findAllByParentIdIsNull();

        Assertions.assertThat(list.get(0).getName()).isSameAs("금속");

        System.out.println(list.get(0).getName());
        System.out.println(list.get(0).getChildren().get(0).getName());
        System.out.println(list.get(0).getChildren().get(0).getChildren().get(0).getName());
    }
}
