package com.b2.prj02.repository;

import com.b2.prj02.entity.Cart;
import com.b2.prj02.entity.CartDetail;
import com.b2.prj02.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    void deleteByProduct(Product product);

    List<CartDetail> findByCartUserEmail(String email);
}
