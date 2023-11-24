package com.b2.prj02.repository.cart;


import com.b2.prj02.entity.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

//    이렇게 하면 Spring Data JPA는 User 엔터티의 userIdx 필드를 기반으로 적절한 쿼리를 생성하여
//    해당 사용자 ID에 대한 장바구니를 찾을 수 있음.
    Optional<CartEntity> findByUser_UserIdx(Long userId);

    //Optional<CartEntity> findByUserId(Long userId);

    //CartDTO findByUser_UserIdx(Long userId);
}
