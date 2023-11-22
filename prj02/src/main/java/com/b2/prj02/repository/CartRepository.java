package com.b2.prj02.repository;

import com.b2.prj02.entity.Cart;
import com.b2.prj02.entity.Category;
import com.b2.prj02.entity.Option;
import com.b2.prj02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);
}
