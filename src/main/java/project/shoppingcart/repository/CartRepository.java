package project.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.shoppingcart.entity.CartEntity;
import project.shoppingcart.entity.CartProductEntity;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;
import java.util.SplittableRandom;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
    // 이름으로 장바구니 검색
    @Query
    List<CartEntity> findByName(@Param("name") String name);
}
