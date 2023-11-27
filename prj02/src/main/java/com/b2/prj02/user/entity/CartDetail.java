package com.b2.prj02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_detail")
public class CartDetail {
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_idx")
    private Long cartProductId;

    @ManyToOne
    @JoinColumn(name = "cart_idx")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_idx")
    private Product product;

    private Integer amount;
    private Double price;

    @OneToOne
    @JoinColumn(name = "option_idx")
    private Option option;


    public void addOptionPrice(Double optionPrice){
        this.price += optionPrice;
    }

    public void setOption(Option option){
        this.option = option;
    }

    public void updateProduct(Integer amount){
        this.amount = amount;
    }
}
