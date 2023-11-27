package com.b2.prj02.shop.option.entity;

import com.b2.prj02.shop.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_option")
public class Option {
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_idx")
    private Long optionId;

    @ManyToOne
    @JoinColumn(name = "product_idx")
    private Product product;

    @Column(name = "product_option")
    private String option;

    @Column(name = "option_price")
    private Double optionPrice;

}
