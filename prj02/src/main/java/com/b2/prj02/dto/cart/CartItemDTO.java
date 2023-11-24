package com.b2.prj02.dto.cart;

//장바구니에 담겨있는 상품들

import com.b2.prj02.dto.product.ProductDTO;
import lombok.Builder;

public class CartItemDTO {

    private Long cartItemId; //카트에 담긴 상품의 고유id
    private Long price; //상품 가격
    private Long quantity; //상품 수량
    private String option; //상품 옵션 S or M or L
    private Long cartId;  // CartEntity에서 가져온 ID
    private ProductDTO product;  // ProductEntity에서 가져온 ID


    @Builder

    public CartItemDTO(Long cartItemId, Long price, Long quantity, String option, Long cartId, ProductDTO product) {
        this.cartItemId = cartItemId;
        this.price = price;
        this.quantity = quantity;
        this.option = option;
        this.cartId = cartId;
        this.product = product;
    }
}
