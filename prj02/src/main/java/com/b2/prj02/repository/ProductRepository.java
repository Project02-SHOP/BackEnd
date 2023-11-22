package com.b2.prj02.repository;

import com.b2.prj02.entity.Category;
import com.b2.prj02.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);

    void deleteByProductName(String productName);
}
