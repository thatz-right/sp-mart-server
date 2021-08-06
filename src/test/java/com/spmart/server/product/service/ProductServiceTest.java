package com.spmart.server.product.service;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.spmart.server.product.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.spmart.server.common.dto.PageDto;
import com.spmart.server.common.dto.PageResponse;
import com.spmart.server.product.domain.Category;
import com.spmart.server.product.domain.OptionValue;
import com.spmart.server.product.domain.Product;
import com.spmart.server.product.domain.ProductOption;
import com.spmart.server.product.dto.ProductCard;
import com.spmart.server.product.dto.ProductDetail;
import com.spmart.server.product.dto.ProductRequest;
import com.spmart.server.product.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@Spy
	private ModelMapper modelMapper;

	@InjectMocks
	private ProductServiceImpl productService;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("카테고리로 상품리스트 가져오기")
	public void getProductCardList() {

		Product product1 = Product.builder()
			.id(1L)
			.code("남405-2")
			.name("원목 니켈스카시상패")
			.image("asdfdsa")
			.category(Category.builder().id(3L).build()) // fk
			.isDisplay(true)
			.isStock(true)
			.price(10000)
			.discountPrice(5000)
			.build();
		Product product2 = Product.builder()
			.id(2L)
			.code("남405-2")
			.name("야호")
			.image("asdfdsa")
			.category(Category.builder().id(4L).build()) // fk
			.isDisplay(true)
			.isStock(true)
			.price(10000)
			.discountPrice(5000)
			.build();

		when(productRepository.findAllByCategoryId(Mockito.anyLong(), Mockito.any(Pageable.class)))
			.thenReturn(new PageImpl(
				Arrays.asList(product1, product2),
				PageRequest.of(1, 2), 3));

		PageDto pageDto = new PageDto(1, 2, 0);
		PageResponse<ProductCard> response = productService.getProductCardList(1L, pageDto);

		verify(productRepository).findAllByCategoryId(eq(1L), Mockito.any(Pageable.class));

		Assertions.assertThat(response.getPageNumber()).isEqualTo(1);
		Assertions.assertThat(response.getPageSize()).isEqualTo(2);
		Assertions.assertThat(response.getTotalPages()).isEqualTo(2);
		Assertions.assertThat(response.getContents().size()).isEqualTo(2);

		Assertions.assertThat(response.getContents().get(0)).isInstanceOf(ProductCard.class);

		for (ProductCard card : response.getContents()) {
			System.out.println(card);
		}
	}

	@Test
	@DisplayName("상품 상세정보 가져오기")
	public void getProductDetail() {
		OptionValue sizeS = OptionValue.builder()
			.id(11L)
			.name("S")
			.extraPrice(12)
			.build();

		ProductOption productOption = ProductOption.builder()
			.id(100L)
			.name("사이즈")
			.build();

		Category category = Category.builder().name("금속").build();

		Category item1 = Category.builder().parent(category).name("동 상패").build();

		Product product1 = Product.builder()
			.id(1L)
			.code("남405-2")
			.name("원목 니켈스카시상패")
			.image("asdfdsa")
			.description("description")
			.category(item1)
			.isDisplay(true)
			.isStock(true)
			.price(10000)
			.discountPrice(5000)
			.build();

		productOption.addOptionValue(sizeS);
		product1.addProductOption(productOption);

		when(productRepository.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(product1));

		ProductDetail productDetail = productService.getProductDetail(1L);
		System.out.println(productDetail);
		verify(productRepository).findById(eq(1L));

		Assertions.assertThat(productDetail.getId()).isEqualTo(1L);
		Assertions.assertThat(productDetail.getCode()).isSameAs("남405-2");
		Assertions.assertThat(productDetail.getName()).isSameAs("원목 니켈스카시상패");
		Assertions.assertThat(productDetail.getDescription()).isSameAs("description");
		Assertions.assertThat(productDetail.getImage()).isSameAs("asdfdsa");
		Assertions.assertThat(productDetail.getOptions().get(0).getName()).isSameAs("사이즈");
		Assertions.assertThat(productDetail.getOptions().get(0).getValues().get(0).getExtraPrice()).isEqualTo(12);
		Assertions.assertThat(productDetail.getOptions().get(0).getValues().get(0).getName()).isSameAs("S");

		System.out.println(productDetail);
	}

	@Test
	@DisplayName("상품 등록하기")
	public void registProduct() {
		Category categoryItem = Category.builder()
			.id(1L)
			.name("카테고리 이름")
			.build();

		OptionValue sizeL = OptionValue.builder()
			.name("S")
			.extraPrice(12)
			.build();

		ProductOption productOption = ProductOption.builder()
			.name("사이즈")
			.build();

		Product product = Product.builder()
			.id(1L)
			.code("남405-2")
			.name("원목 니켈스카시상패")
			.description("description")
			.category(categoryItem)
			.image("test")
			.isDisplay(true)
			.isStock(true)
			.price(10000)
			.discountPrice(5000)
			.build();

		productOption.addOptionValue(sizeL);
		product.addProductOption(productOption);

		ProductRequest productRequest = modelMapper.map(product, ProductRequest.class);

		when(productRepository.save(Mockito.any(Product.class)))
			.thenReturn(product);

		when(categoryRepository.existsById(Mockito.anyLong()))
			.thenReturn(true);

		productService.registProduct(productRequest);

		verify(productRepository).save(Mockito.any(Product.class));
		verify(categoryRepository).existsById(Mockito.anyLong());
	}

	@Test
	@DisplayName("상품 등록하기 실패한 경우")
	public void registProductFail() {
		Category categoryItem = Category.builder()
			.name("카테고리 이름")
			.build();

		OptionValue sizeL = OptionValue.builder()
			.name("L")
			.extraPrice(0)
			.build();

		ProductOption productOption = ProductOption.builder()
			.name("사이즈")
			.build();

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

		ProductRequest productRequest = modelMapper.map(product, ProductRequest.class);

		when(productRepository.save(Mockito.any(Product.class)))
			.thenReturn(product);

		when(categoryRepository.save(Mockito.any(Category.class)))
			.thenReturn(categoryItem);

		Assert.assertThrows(IllegalArgumentException.class, () ->
				productService.registProduct(productRequest)
		);
	}

	@Test
	@DisplayName("상품정보 업데이트")
	public void updateProduct() {
		Category categoryItem = Category.builder()
			.id(1L)
			.name("카테고리 이름")
			.build();

		OptionValue sizeL = OptionValue.builder()
			.name("S")
			.extraPrice(12)
			.build();

		ProductOption productOption = ProductOption.builder()
			.name("사이즈")
			.build();

		Product product = Product.builder()
			.id(1L)
			.code("남405-2")
			.name("원목 니켈스카시상패")
			.description("description")
			.category(categoryItem)
			.image("test")
			.isDisplay(true)
			.isStock(true)
			.price(10000)
			.discountPrice(5000)
			.build();

		productOption.addOptionValue(sizeL);
		product.addProductOption(productOption);

		ProductRequest productRequest = modelMapper.map(product, ProductRequest.class);

		when(productRepository.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(product));
		when(productRepository.save(Mockito.any(Product.class)))
			.thenReturn(Mockito.any(Product.class));

		productService.updateProduct(productRequest);

		verify(productRepository).save(Mockito.any(Product.class));
		verify(productRepository).findById(Mockito.anyLong());
	}
}
