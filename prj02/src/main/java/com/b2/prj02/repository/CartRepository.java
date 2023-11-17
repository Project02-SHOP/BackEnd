package com.b2.prj02.repository;

import com.b2.prj02.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
    // 이름으로 장바구니 검색
    @Query
    List<CartEntity> findByName(@Param("name") String name);
}