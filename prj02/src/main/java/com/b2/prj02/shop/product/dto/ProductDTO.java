package com.b2.prj02.shop.product.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productName;
    private Double price;
    private String category;
    private Integer productQuantity;
}
