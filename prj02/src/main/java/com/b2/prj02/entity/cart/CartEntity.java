package com.b2.prj02.entity.cart;


import com.b2.prj02.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "cart")
public class CartEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_idx")
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItems = new ArrayList<>();

    private Long totalPrice; // 장바구니 총가격
    private Long totalQuantity;   // 장바구니 총수량--total_quantity로 db 컬럼명 바꾸기



    @Builder
    public CartEntity(Long cartId, User user, List<CartItemEntity> cartItems, Long totalPrice, Long totalQuantity) {
        this.cartId = cartId;
        this.user = user;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }



    public void addCartItems(CartItemEntity cartItem){
        if(this.cartItems == null){
            this.cartItems = new ArrayList<>();
        }
        this.cartItems.add(cartItem);
    }

    public CartEntity createCart(User cartUser) {
        return CartEntity.builder()
                .cartId(this.cartId)
                .user(cartUser)
                .cartItems(new ArrayList<>())
                .totalPrice(this.totalPrice)
                .totalQuantity(this.totalQuantity)
                .build();
    }


}

