package com.b2.prj02.repository;

import com.b2.prj02.entity.Cart;
import com.b2.prj02.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

}
