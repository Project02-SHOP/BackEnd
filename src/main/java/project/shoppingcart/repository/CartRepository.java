package project.shoppingcart.repository;

import java.util.HashMap;
import java.util.Map;

public class CartRepository {

    private Map<String, Double> productPrice = new HashMap<>();

    // 레포지토리에 상품 정보 추가
    public void addProduct(String productName, double price){
        productPrice.put(productName, price);
    }


    // 레포지토리에서 상품 가격 조회
    public double getProductPrice(String productName){
        return productPrice.getOrDefault(productName, 0.0);
    }
}
