package com.b2.prj02.option.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionDTO {
    private String productName;
    private String option;
    private Double optionPrice;
}
