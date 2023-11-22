package com.b2.prj02.dto.Cart;

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
    private Long cartProductId;
    private String productName;
    private Integer amount;
    private String option;
}
