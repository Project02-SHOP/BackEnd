package com.b2.prj02.order.repository;

import com.b2.prj02.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserEmail(String email);

    @Query("SELECT sum(o.price) as totalPrice, sum(o.amount) as totalAmount, count(o.orderId) as totalOrder FROM Order o WHERE o.user.email = : email")
    Tuple total(@Param("email") String email);
}
