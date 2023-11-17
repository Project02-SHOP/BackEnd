package project.shoppingcart.service;

<<<<<<< HEAD
=======
import org.hibernate.annotations.Comment;
>>>>>>> 4f9acfb (search)
import project.shoppingcart.DTO.CartDTO;
import project.shoppingcart.entity.CartEntity;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;


@Service
@Component
// 상품 서비스 정의
public interface CartService {
<<<<<<< HEAD
    CartDTO addItemToCart(CartDTO cartDTO, int quantity, Map<String, String> options);

    CartDTO addItemToCart(CartEntity cartEntity, int quantity, Map<String, String> options);

    Object viewCart();
    CartEntity addToCart(CartEntity cartItem);
=======
    CartDTO addItemToCart(CartEntity cartEntity, int quantity, Map<String, String> options);

    Object viewCart();
    CartDTO addToCart(CartEntity cartItem);
>>>>>>> 4f9acfb (search)
}



//public interface CartRepository extends JpaRepository<CartEntity, Long> {
//  CartEntity findByUserIdxAndProductIdAndCartStatus(Long userIdx, Long productId, String cart);
// }}
