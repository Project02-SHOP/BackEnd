package com.b2.prj02.cart.repository;

import com.b2.prj02.cart.entity.Cart;
import com.b2.prj02.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

    Optional<Cart> findByUserEmail(String email);

    void deleteByUser(User user);
}
