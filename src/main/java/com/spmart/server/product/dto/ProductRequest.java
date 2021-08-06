package com.spmart.server.product.dto;

import com.spmart.server.product.domain.Category;
import com.spmart.server.product.domain.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequest {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private Long categoryId;
    private boolean isDisplay;
    private boolean isStock;
    private List<OptionInfo> options;
    private int discountPrice;
    private int price;

    public Product toEntity() {
        Product product = Product.builder()
                .id(id)
                .code(code)
                .name(name)
                .description(description)
                .image(image)
                .category(Category.builder().id(categoryId).build())
                .isDisplay(isDisplay).isStock(isStock).discountPrice(discountPrice).price(price).build();

        for (OptionInfo optionInfo : options) {
            product.addProductOption(optionInfo.toEntity());
        }

        return product;
    }
}
