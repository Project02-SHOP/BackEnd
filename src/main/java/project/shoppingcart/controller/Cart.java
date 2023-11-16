package project.shoppingcart.controller;

import java.util.Map;

public class Cart {
    // 상품 서비스 정의
    interface CartService {
        void addItemToCart(Cart cart, int quantity, Map<String, String> options);
    }
}
