package com.b2.prj02.shop.product.repository;

import com.b2.prj02.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);

    void deleteByProductId(Long productId);

    Optional<Product> findByProductId(Long productId);
}
