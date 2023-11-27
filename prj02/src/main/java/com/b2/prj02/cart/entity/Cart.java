package com.b2.prj02.cart.entity;

import com.b2.prj02.user.entity.User;
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

    public void updateCart(Integer amount, Double totalPrice){
        this.totalAmount += amount;
        this.totalPrice += totalPrice;
    }
}
