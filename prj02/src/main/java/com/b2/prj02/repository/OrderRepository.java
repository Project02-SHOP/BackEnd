package com.b2.prj02.repository;

import com.b2.prj02.entity.Cart;
import com.b2.prj02.entity.Order;
import com.b2.prj02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserEmail(String email);

    @Query("SELECT sum(o.price) as totalPrice, sum(o.amount) as totalAmount, count(o.orderId) as totalOrder FROM Order o WHERE o.user.email = : email")
    Tuple total(@Param("email") String email);
}
