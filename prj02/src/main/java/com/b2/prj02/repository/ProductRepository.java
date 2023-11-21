package com.b2.prj02.repository;

import com.b2.prj02.entity.product.ProductEntity;
import com.b2.prj02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    //특정 사용자의 판매 상품 조회
    List<ProductEntity> findByUserIdAndSaleEndDateBefore(User userId,LocalDateTime currentDate);


    // 판매자 아이디와 판매 종료 날짜가 지난 상품 조회
    List<ProductEntity> findByUserIdAndSaleEndDateAfter(User userId, LocalDateTime saleEndDate);




}
