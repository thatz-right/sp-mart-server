package com.spmart.server.product;

import com.spmart.server.product.entity.Category;
import com.spmart.server.product.entity.OptionValue;
import com.spmart.server.product.entity.Product;
import com.spmart.server.product.entity.ProductOption;
import com.spmart.server.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

@DataJpaTest
class ProductTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("제품 생성")
    public void addProductTest() {
        Category category = Category.builder()
                .name("카테로기")
                .build();

        OptionValue sizeL = OptionValue.builder()
                .value("L")
                .build();

        OptionValue sizeM = OptionValue.builder()
                .value("M")
                .build();
        
        OptionValue sizeS = OptionValue.builder()
                .value("S")
                .build();

        ProductOption productOption  = ProductOption.builder()
                .name("사이즈")
                .values(Arrays.asList(sizeL, sizeM, sizeS))
                .build(); // L, M, S

        Product product = Product.builder()
                .code("남405-2")
                .name("원목 니켈스카시상패")
                .image("asdfdsa")
                .category(category) // fk
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