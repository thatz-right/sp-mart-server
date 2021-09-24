package com.spmart.server.product.dto;

import com.spmart.server.product.domain.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryRequest {
    private Long id;

    private String name;

    private Long parentId;

    public Category toEntity() {
        Category.CategoryBuilder cb = Category.builder().id(id).name(name);

        if(parentId != null) {
            cb.parent(Category.builder().id(parentId).build());
        }

        return cb.build();
    }
}

