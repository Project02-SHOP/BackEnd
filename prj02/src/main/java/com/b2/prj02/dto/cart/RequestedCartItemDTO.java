package com.b2.prj02.dto.cart;


import com.b2.prj02.entity.cart.CartItemEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestedCartItemDTO {
    private Long productId;
    private Long quantity; //상품 수량
    private String option; //상품 옵션 S or M or L


    @Builder
    public RequestedCartItemDTO(Long productId, Long quantity, String option) {
        this.productId = productId;
        this.quantity = quantity;
        this.option = option;
    }




    /*
    public static RequestedCartItemDTO toCartRequestItemDTO(CartItemEntity cartItem){
        return RequestedCartItemDTO.builder()
                .productId(cartItem.getProduct().getProductId())
                .quantity(cartItem.getQuantity())
                .option(cartItem.getOption())
                .build();
    }
*/



}
