package project.shoppingcart.entity;

import lombok.Getter;

// 상품 엔터티 정의
public class CartEntity {

    @Getter
    private String name;

    @Getter
    private double price;

    public CartEntity(String name, double price){
        this.name = name;
        this.price = price;
    }

    public CartEntity() {

    }

    public String getName() {
        return name;
    }
}

//import lombok.Data;
//import lombok.NoArgsConstructor;
//import javax.persistence.*;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "cart")
//public class CartEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long cartId;
//
//    @Column(name = "user_idx", nullable = false)
//    private Long userIdx;
//
//    @Column(name = "product_id", nullable = false)
//    private Long productId;
//
//    @Column(name = "cart_quantity", nullable = false) //상품 수량
//    private int cartQuantity;
//
//    @Column(name = "cart_status", nullable = false)  //결제여부
//    private String cartStatus;
//
//    @Column(name = "total_price", nullable = false)
//    private int totalPrice;
//
//    public CartEntity(Long cartId, Long userIdx, Long productId, int cartQuantity, String cartStatus, int totalPrice) {
//        this.cartId = cartId;
//        this.userIdx = userIdx;
//        this.productId = productId;
//        this.cartQuantity = cartQuantity;
//        this.cartStatus = cartStatus;
//        this.totalPrice = totalPrice;
//    }
//}
