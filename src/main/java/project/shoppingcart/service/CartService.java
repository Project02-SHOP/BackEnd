package project.shoppingcart.service;

import project.shoppingcart.DTO.CartDTO;
import project.shoppingcart.entity.CartEntity;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;


@Service
@Component
// 상품 서비스 정의
public interface CartService {
    CartDTO addItemToCart(CartDTO cartDTO, int quantity, Map<String, String> options);

    CartDTO addItemToCart(CartEntity cartEntity, int quantity, Map<String, String> options);

    Object viewCart();
    CartEntity addToCart(CartEntity cartItem);
}



//public interface CartRepository extends JpaRepository<CartEntity, Long> {
//  CartEntity findByUserIdxAndProductIdAndCartStatus(Long userIdx, Long productId, String cart);
// }}
