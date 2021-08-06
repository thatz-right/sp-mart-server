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
		Category parent = Category.builder().name("금속").build(); //

		Category saved = categoryRepository.save(parent);

		Category item1 = Category.builder().parent(parent).name("동 상패").build();
		Category item2 = Category.builder().parent(parent).name("은 상패").build();
		Category item3 = Category.builder().parent(parent).name("금 상패").build();

		categoryRepository.saveAll(Arrays.asList(item1, item2, item3));

		List<Category> list = categoryRepository.findAllByParentId(parent.getId());// 바보!

		Assertions.assertThat(list.size()).isEqualTo(3);

		list.forEach(item -> {
			System.out.println(item.getName());
		});
	}

	@Test
	@DisplayName("카테고리 리스트 불러오기")
	public void selectAll() {
		categoryRepository.saveAll(Arrays.asList(
			Category.builder().name("금속").build(),
			Category.builder().name("크리스탈").build(),
			Category.builder().name("목재").build()
		));

		List<Category> list = categoryRepository.findAllByOrderById();
	}

	@Test
	@DisplayName("카테고리 id로 세부목록 받아오기")
	public void selectByCategoryId() {

		Category category = categoryRepository.save(Category.builder().name("금속").build());

		Category item1 = Category.builder().parent(category).name("동 상패").build();
		Category item2 = Category.builder().parent(category).name("은 상패").build();
		Category item3 = Category.builder().parent(category).name("금 상패").build();

		categoryRepository.saveAll(Arrays.asList(item1, item2, item3));

		List<Category> list = categoryRepository.findAllByParentId(category.getId());

		Assertions.assertThat(list.size()).isEqualTo(3);
		list.forEach(categoryItem -> System.out.println(categoryItem.getName()));
	}
}
