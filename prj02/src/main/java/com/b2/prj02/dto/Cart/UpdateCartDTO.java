package com.b2.prj02.dto.Cart;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartDTO {
    private String productName;
    private Integer amount;
}
