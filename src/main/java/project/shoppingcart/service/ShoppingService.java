package project.shoppingcart.service;

import project.shoppingcart.DTO.CartDTO;
import project.shoppingcart.entity.CartEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingService implements CartService {
    private List<CartEntity> products = new ArrayList<>();
    private List<Integer> quantities = new ArrayList<>();
    private Map<CartEntity, Map<String, String>> optionsMap = new HashMap<>();


    // 카트에 상품을 추가하는 메서드
    @Override
    public CartDTO addItemToCart(CartEntity cartEntity, int quantity, Map<String, String> options) {
        products.add(cartEntity);
        quantities.add(quantity);
        optionsMap.put(cartEntity, options);
        System.out.println(quantity + "개의 " + cartEntity.getName() + "이(가) 카트에 추가되었습니다.");

        if (options != null && !options.isEmpty()) {
            System.out.println("옵션: " + options);
        }
        return (CartDTO) cartEntity;
    }


    // 카트 내용을 보여주는 메서드
    public Object viewCart() {
        System.out.println("-------쇼핑카트---------");
        double totalAmount = 0;

        for (int i = 0; i < products.size(); i++) {
            CartEntity cartEntity = products.get(i);
            int quantity = quantities.get(i);
            double subtotal = cartEntity.getPrice() * quantity;

            System.out.println(cartEntity.getName() + " - " + quantity + "개 - 가격: $" + cartEntity.getPrice() +
                    " - 소계: $" + subtotal);

            Map<String, String> options = optionsMap.get(cartEntity);
            if (options != null && !options.isEmpty()) {
                System.out.println("옵션: " + options);
            }

            totalAmount += subtotal;
        }

        System.out.println("총액: $" + totalAmount);
        System.out.println("--------------------------");
        return null;
    }

    @Override
    public CartDTO addToCart(CartEntity cartItem) {
        return null;
    }

}


