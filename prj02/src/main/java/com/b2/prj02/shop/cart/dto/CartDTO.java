package com.b2.prj02.shop.cart.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long productId;
    private Integer amount;
    private String option;
}
