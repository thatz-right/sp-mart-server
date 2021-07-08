package com.spmart.server.product.repository;

import java.util.Arrays;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spmart.server.product.domain.CategoryItem;
import com.spmart.server.product.domain.OptionValue;
import com.spmart.server.product.domain.Product;
import com.spmart.server.product.domain.ProductOption;

@DataJpaTest
class ProductTest {
	@Autowired
	ProductRepository productRepository;

	@Test
	@DisplayName("제품 생성")
	public void addProductTest() {
		CategoryItem categoryItem = CategoryItem.builder()
			.name("카테고리 이름")
			.build();

		OptionValue sizeL = OptionValue.builder()
			.name("L")
			.extraPrice(0)
			.build();

		OptionValue sizeM = OptionValue.builder()
			.name("M")
			.extraPrice(0)
			.build();

		OptionValue sizeS = OptionValue.builder()
			.name("S")
			.extraPrice(0)
			.build();

		ProductOption productOption = ProductOption.builder()
			.name("사이즈")
			.values(Arrays.asList(sizeL, sizeM, sizeS))
			.build(); // L, M, S

		Product product = Product.builder()
			.code("남405-2")
			.name("원목 니켈스카시상패")
			.image("asdfdsa")
			.categoryItem(categoryItem) // fk
			.isDisplay(true)
			.isStock(true)
			.options(Arrays.asList(productOption)) // 사이즈 - S, M, L
			.price(10000)
			.discountPrice(5000)
			.build();

		Product savedProduct = productRepository.save(product);

		Product findProduct = productRepository.findById(savedProduct.getId())
			.orElseThrow(EntityNotFoundException::new);

		Assertions.assertThat(savedProduct).isSameAs(findProduct);

	}

}