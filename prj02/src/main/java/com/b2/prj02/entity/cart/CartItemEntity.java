package com.b2.prj02.entity.cart;

import com.b2.prj02.dto.cart.RequestedCartItemDTO;
import com.b2.prj02.entity.product.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "cart_item") //cart_item 으로 테이블명 바꾸기
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_idx")
    private Long cartItemId; //카트에 들어있는 상품의 id

    // 어느 유저의 카트인지에 대한 정보
    // 이 상품이 어느 카트에 담길지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    //상품 정보
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    //수량 -- 컬럼명 quantity로 바꾸기
    private Long quantity;

    //가격 -- 수량까지 곱해준 가격인듯..,,?
    @Column(name = "price")
    private  Long totalPriceOfCartItem;

    //옵션
    private  String option;



    @Builder
    public CartItemEntity(Long cartItemId, ProductEntity product, CartEntity cart, Long quantity, Long totalPriceOfCartItem, String option) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
        this.totalPriceOfCartItem = (long) ((product.getPrice())*quantity); //수량에 따라 해당 상품의 총가격이 달라짐.
        this.option = option;
    }

    @Builder
    public CartItemEntity(CartEntity cart, ProductEntity product, Long quantity, Long totalPriceOfCartItem, String option) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.totalPriceOfCartItem = (long) (product.getPrice()*quantity);
        this.option = option;
    }





    public static CartItemEntity requestedItemDTOToEntity(ProductEntity product, RequestedCartItemDTO item, CartEntity cart){
        return CartItemEntity.builder()
                .product(product)
                .cart(cart)
                .quantity(item.getQuantity())
                .totalPriceOfCartItem((long) (item.getQuantity()*product.getPrice()))
                .option(item.getOption())
                .build();
    }




}

