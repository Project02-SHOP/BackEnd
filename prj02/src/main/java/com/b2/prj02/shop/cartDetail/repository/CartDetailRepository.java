package com.b2.prj02.shop.cartDetail.repository;

import com.b2.prj02.shop.cartDetail.entity.CartDetail;
import com.b2.prj02.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    void deleteByProduct(Product product);

    List<CartDetail> findByCartUserEmail(String email);
}
