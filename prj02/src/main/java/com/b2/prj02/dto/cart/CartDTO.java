package com.b2.prj02.dto.cart;


import com.b2.prj02.entity.User;
import com.b2.prj02.entity.cart.CartEntity;
import com.b2.prj02.entity.cart.CartItemEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

//회원의 장바구니 정보
@Getter
@NoArgsConstructor
public class CartDTO {

    private User cartUser;
    private List<CartItemDTO> cartItems;
    private Long totalPrice; //각각 상품의 총가격(수량*상품가)을 모두 더한 것.
    private Long totalQuantity; //카트에 담긴 상품들의 총수량.
    //클라이언트 쪽에서는 장바구니 번호가 필요없음.


    @Builder
    public CartDTO(User cartUser, List<CartItemDTO> cartItems, Long totalPrice, Long totalQuantity) {
        this.cartUser = cartUser;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }




//카트에 담긴 -- cartId, user, cartItems, totalPrice, totalQuantity
    public static CartDTO cartEntityToDTO(CartEntity cartEntity) {
        return CartDTO.builder()
                .cartUser(cartEntity.getUser())
                .cartItems(toCartItemDTOList(cartEntity.getCartItems())) //List<CartItemEntity>
                .totalPrice(cartEntity.getTotalPrice())
                .totalQuantity(cartEntity.getTotalQuantity())
                .build();
    }


    private static List<CartItemDTO> toCartItemDTOList(List<CartItemEntity> cartItemEntities) {
        return cartItemEntities.stream()
                .map(cartItemEntity -> CartItemDTO.builder()
                        .cartItemId(cartItemEntity.getCartItemId())
                        .price(cartItemEntity.getTotalPriceOfCartItem())
                        .quantity(cartItemEntity.getQuantity())
                        .option(cartItemEntity.getOption())
                        .cartId(cartItemEntity.getCart().getCartId())
                        .product(cartItemEntity.getProduct().toDto())
                        .build())
                .collect(Collectors.toList());
    }


}
