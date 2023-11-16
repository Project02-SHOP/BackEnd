package com.b2.prj02.repository;

import com.b2.prj02.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    //특정 기간에 등록된 제품을 검색
    List<ProductEntity> findByRegisterDateBetween(LocalDateTime registerDate, LocalDateTime saleEndDate);

    //특정 날짜에 등록된 제품을 검색
    List<ProductEntity> findByRegisterDate(LocalDateTime date);

    // 기간에 따른 제품 검색 및 정렬
    List<ProductEntity> findByRegisterDateBetweenOrderByPriceAsc(LocalDateTime registerDate, LocalDateTime saleEndDate);
}
