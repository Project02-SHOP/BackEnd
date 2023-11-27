package com.b2.prj02.cart.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private String productName;
    private Integer amount;
    private String option;
}
