package com.b2.prj02.shop.option.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOptionDTO {
    private String productName;
    private String option;
}
