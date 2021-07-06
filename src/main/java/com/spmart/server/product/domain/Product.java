package com.spmart.server.product.domain;

import com.spmart.server.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;

    private String name;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean isDisplay;

    private boolean isStock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> options;

    private int discountPrice;

    private int price;

    @Builder
    public Product(String code, String name, String image, Category category, boolean isDisplay, boolean isStock, List<ProductOption> options, int discountPrice, int price) {
        this.code = code;
        this.name = name;
        this.image = image;
        this.category = category;
        this.isDisplay = isDisplay;
        this.isStock = isStock;
        this.options = options;
        this.discountPrice = discountPrice;
        this.price = price;
    }
}
