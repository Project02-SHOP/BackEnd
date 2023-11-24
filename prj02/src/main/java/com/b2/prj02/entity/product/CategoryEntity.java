package com.b2.prj02.entity.product;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "category")
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_idx")
    private Long categoryId;


    @Column(name = "category_name")
    private Long categoryName;

    @Builder
    public CategoryEntity(Long categoryId, Long categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }


}
