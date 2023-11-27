package com.b2.prj02.shop.option.repository;

import com.b2.prj02.shop.option.entity.Option;
import com.b2.prj02.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {


    void deleteByOption(String option);

    Optional<Option> findByProduct(Product product);

    Optional<Option> findByOptionAndProduct(String option, Product product);
}
