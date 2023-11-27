package com.b2.prj02.product.dto;

import com.b2.prj02.entity.Category;
import lombok.*;

import java.sql.Timestamp;

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
