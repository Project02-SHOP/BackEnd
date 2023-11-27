package com.b2.prj02.cart.dto;

import com.b2.prj02.entity.Cart;
import com.b2.prj02.entity.Option;
import com.b2.prj02.entity.Product;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

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
