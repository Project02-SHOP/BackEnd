package project.shoppingcart.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.shoppingcart.entity.CartEntity;
import project.shoppingcart.entity.CartProductEntity;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;
import java.util.SplittableRandom;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
    // 이름으로 장바구니 검색
    @Query
    List<CartEntity> findByName(@Param("name") String name);
=======
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
>>>>>>> 4f9acfb (search)
}
