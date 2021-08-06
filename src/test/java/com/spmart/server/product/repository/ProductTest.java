package com.spmart.server.product.repository;

import com.spmart.server.product.domain.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.spmart.server.product.domain.OptionValue;
import com.spmart.server.product.domain.Product;
import com.spmart.server.product.domain.ProductOption;

@DataJpaTest
@ActiveProfiles("test")
class ProductTest {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductOptionRepository productOptionRepository;

	@Autowired
	OptionValueRepository optionValueRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	@DisplayName("상품 생성")
	public void addProductTest() {
		Category categoryItem = Category.builder()
			.name("카테고리 이름")
			.build();

		categoryRepository.save(categoryItem);

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
			.build(); // L, M, S

		Product product = Product.builder()
			.code("남405-2")
			.name("원목 니켈스카시상패")
			.image("asdfdsa")
			.category(categoryItem) // fk
			.isDisplay(true)
			.isStock(true)
			.price(10000)
			.discountPrice(5000)
			.build();

		product.addProductOption(productOption);
		productOption.addOptionValue(sizeL);
		productOption.addOptionValue(sizeM);
		productOption.addOptionValue(sizeS);

		Product savedProduct = productRepository.save(product);

		Product findProduct = productRepository.findById(savedProduct.getId()).orElseThrow();

		ProductOption findOption = productOptionRepository.findByProductId(savedProduct.getId()).orElseThrow();

		ModelMapper modelMapper = new ModelMapper();

		for (OptionValue value :
			productOption.getValues()) {
			System.out.println("optionValue id = " + value.getId());
			System.out.println("optionValue id = " + value.getProductOption().getId());
		}

		Assertions.assertThat(findOption.getProduct()).isSameAs(savedProduct);
		Assertions.assertThat(savedProduct).isSameAs(findProduct);
	}

	@Test
	@DisplayName("상품 업데이트 테스트")
	public void updateProductTest() {
		Category categoryItem = Category.builder()
			.name("카테고리 이름")
			.build();

		categoryRepository.save(categoryItem);

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
			.build(); // L, M, S

		Product product = Product.builder()
			.code("남405-2")
			.name("원목 니켈스카시상패")
			.image("asdfdsa")
			.category(categoryItem)
			.isDisplay(true)
			.isStock(true)
			.price(10000)
			.discountPrice(5000)
			.build();

		product.addProductOption(productOption);
		productOption.addOptionValue(sizeL);
		productOption.addOptionValue(sizeM);
		productOption.addOptionValue(sizeS);

		Product savedProduct = productRepository.saveAndFlush(product);

		Long productOptionId = productOption.getId();
		Product product2 = Product.builder()
			.code("남405-2")
			.name("new 이름")
			.image("new 이미지")
			.category(categoryItem)
			.isDisplay(true)
			.isStock(true)
			.price(15000)
			.discountPrice(10000)
			.build();

		Product findProduct = productRepository.findById(savedProduct.getId()).get();

		findProduct.update(product2);

		Product updatedProduct = productRepository.save(findProduct);

		boolean isExist = productOptionRepository.existsById(productOptionId);
		boolean isExistValue = optionValueRepository.existsById(sizeL.getId());

		Assertions.assertThat(updatedProduct.getName()).isSameAs("new 이름");
		Assertions.assertThat(isExist).isFalse();
		Assertions.assertThat(isExistValue).isFalse();
	}

	@Test
	@DisplayName("상품 제거")
	public void removeProductTest() {
		Category categoryItem = Category.builder()
			.name("카테고리 이름")
			.build();

		categoryRepository.save(categoryItem);

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
			.build(); // L, M, S

		Product product = Product.builder()
			.code("남405-2")
			.name("원목 니켈스카시상패")
			.image("asdfdsa")
			.category(categoryItem) // fk
			.isDisplay(true)
			.isStock(true)
			.price(10000)
			.discountPrice(5000)
			.build();

		product.addProductOption(productOption);
		productOption.addOptionValue(sizeL);
		productOption.addOptionValue(sizeM);
		productOption.addOptionValue(sizeS);

		Product savedProduct = productRepository.saveAndFlush(product);

		ProductOption productOption1 = productOptionRepository.findById(product.getOptions().get(0).getId()).get();
		// productRepository.delete(savedProduct);
		productRepository.delete(Product.builder().id(savedProduct.getId()).build());

		long optionId = productOption1.getId();
		long optionValue1Id = sizeL.getId();

		boolean isExistProduct = productRepository.existsById(savedProduct.getId());
		boolean isExistOption = productOptionRepository.existsById(optionId);
		boolean isExistOptionValue = optionValueRepository.existsById(optionValue1Id);
		boolean isExistCategoryItem = categoryRepository.existsById(categoryItem.getId());

		Assertions.assertThat(isExistProduct).isFalse();
		Assertions.assertThat(isExistOption).isFalse();
		Assertions.assertThat(isExistOptionValue).isFalse();
		Assertions.assertThat(isExistCategoryItem).isTrue();
	}
}