package com.b2.prj02.shop.cart.entity;

import com.b2.prj02.shop.cartDetail.entity.CartDetail;
import com.b2.prj02.shop.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_idx")
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetailList;


    public void updateCart(Integer amount, Double totalPrice){
        if(this.totalAmount == null)
            this.totalAmount=0;

        if(this.totalPrice == null)
            this.totalPrice=0.0;
        this.totalAmount += amount;
        this.totalPrice += totalPrice;
    }
}
