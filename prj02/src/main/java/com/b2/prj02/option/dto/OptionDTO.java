package com.b2.prj02.dto.Option;

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
