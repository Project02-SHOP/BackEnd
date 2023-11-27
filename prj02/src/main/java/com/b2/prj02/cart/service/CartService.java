package com.b2.prj02.cart.service;

import com.b2.prj02.cart.dto.CartDTO;
import com.b2.prj02.cart.dto.DeleteCartDTO;
import com.b2.prj02.cart.entity.Cart;
import com.b2.prj02.cart.repository.CartRepository;
import com.b2.prj02.cartDetail.entity.CartDetail;
import com.b2.prj02.cartDetail.repository.CartDetailRepository;
import com.b2.prj02.config.security.jwt.JwtTokenProvider;
import com.b2.prj02.option.entity.Option;
import com.b2.prj02.option.repository.OptionRepository;
import com.b2.prj02.order.entity.Order;
import com.b2.prj02.order.repository.OrderRepository;
import com.b2.prj02.product.entity.Product;
import com.b2.prj02.product.repository.ProductRepository;
import com.b2.prj02.user.entity.User;
import com.b2.prj02.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OptionRepository optionRepository;
    private final CartDetailRepository cartDetailRepository;
    private final OrderRepository orderRepository;
    public ResponseEntity<?> addProductToCart(CartDTO cartDTO, String token) {
        Optional<Product> product = productRepository.findByProductName(cartDTO.getProductName());
        String email = jwtTokenProvider.findEmailBytoken(token);
        Optional<User> user = userRepository.findByEmail(email);

        if(product.isEmpty())
            throw new DisabledException("상품명을 확인해주세요.");

        if(email==null || user.isEmpty())
            throw new DisabledException("로그인을 다시 해주세요.");

        Optional<Cart> cart = cartRepository.findByUser(user.get());

        if(cart.isEmpty())
            addCart(user.get());

        if(cart.isPresent()) {
            CartDetail newProduct = CartDetail.builder()
                    .cart(cart.get())
                    .product(product.get())
                    .price(product.get().getPrice())
                    .amount(cartDTO.getAmount())
                    .build();


            if(cartDTO.getOption()!=null){
                Optional<Option> option = optionRepository.findByOptionAndProduct(cartDTO.getOption(), product.get());

                option.ifPresent(newProduct::setOption);
                option.ifPresent(value -> newProduct.addOptionPrice(value.getOptionPrice()));
            }

            cartDetailRepository.save(newProduct);

            cart.get().updateCart(newProduct.getAmount(), newProduct.getPrice());
            cartRepository.save(cart.get());
        }
        return ResponseEntity.status(200).body("상품이 성공적으로 추가되었습니다.");
    }

    public void addCart(User user){
        Cart newCart = Cart.builder()
                            .user(user)
                            .build();

        cartRepository.save(newCart);
    }

    public ResponseEntity<?> deleteProductToCart(DeleteCartDTO deleteCartDTO, String token) {
        Optional<Product> product = productRepository.findByProductName(deleteCartDTO.getProductName());
        String email = jwtTokenProvider.findEmailBytoken(token);
        Optional<User> user = userRepository.findByEmail(email);

        if(product.isEmpty())
            throw new DisabledException("상품명을 확인해주세요.");

        if(email==null || user.isEmpty())
            throw new DisabledException("로그인을 다시 해주세요.");
//        토큰값의 카트 유저의 카트에 상품이 있는지
        List<CartDetail> userCartProductList = cartDetailRepository.findByCartUserEmail(email);

        for (CartDetail userCartProduct : userCartProductList) {
            if (userCartProduct.getProduct().equals(product.get())) {
                cartDetailRepository.deleteByProduct(product.get());
                return ResponseEntity.status(200).body("상품이 성공적으로 제거되었습니다.");
            }
        }
        throw new DisabledException("고객님의 장바구니에 해당 물품이 없습니다.");
    }

    public ResponseEntity<?> updateProductToCart(CartDTO cartDTO, String token) {
        Optional<Product> product = productRepository.findByProductName(cartDTO.getProductName());
        String email = jwtTokenProvider.findEmailBytoken(token);
        Optional<User> user = userRepository.findByEmail(email);

        if(product.isEmpty())
            throw new DisabledException("상품명을 확인해주세요.");

        if(email==null || user.isEmpty())
            throw new DisabledException("로그인을 다시 해주세요.");

        List<CartDetail> userCartProductList = cartDetailRepository.findByCartUserEmail(email);

        for (CartDetail cartDetail : userCartProductList) {
            if (cartDetail.getProduct().equals(product.get())) {
                cartDetail.updateProduct(cartDTO.getAmount());
                cartDetailRepository.save(cartDetail);
                return ResponseEntity.status(200).body("상품이 성공적으로 수정되었습니다.");
            }
        }
        throw new DisabledException("고객님의 장바구니에 해당 물품이 없습니다.");
    }


    public ResponseEntity<?> findAllMyCartDetail(String token) {
        String email = jwtTokenProvider.findEmailBytoken(token);
        List<CartDetail> userCartProductList = cartDetailRepository.findByCartUserEmail(email);

        return ResponseEntity.status(200).body(userCartProductList);
    }

    public ResponseEntity<?> findAllMyCart(String token) {
        String email = jwtTokenProvider.findEmailBytoken(token);
        Optional<Cart> myCart = cartRepository.findByUserEmail(email);

        if(myCart.isPresent())
            return ResponseEntity.status(200).body(myCart.get());

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            addCart(user.get());
            return ResponseEntity.status(200).body(cartRepository.findByUserEmail(email));
        }

        throw new DisabledException("로그인을 다시 해주세요.");
    }

    @Transactional
    public ResponseEntity<?> payMyCart(String token) {
        String email = jwtTokenProvider.findEmailBytoken(token);
        Optional<Cart> myCart = cartRepository.findByUserEmail(email);
        Optional<User> user = userRepository.findByEmail(email);

//        계산 User의 paymoney에서 totalmoney 빼고 저장
//        cartDetail의 상품의 수량, 옵션이면 옵션 수량에서 빼고 저장
        if(user.isEmpty())
            throw new DisabledException("로그인을 다시 해주세요.");

        if(myCart.isEmpty())
            throw new DisabledException("장바구니가 비어있습니다.");

        user.get().buy(myCart.get().getTotalPrice());

        List<CartDetail> userCartProductList = cartDetailRepository.findByCartUserEmail(email);
        for (CartDetail userCartProduct : userCartProductList) {
            Optional<Product> product = productRepository.findByProductName(userCartProduct.getProduct().getProductName());
            product.get().buy(userCartProduct.getAmount());
            Order order = Order.builder()
                    .productName(userCartProduct.getProduct().getProductName())
                    .amount(userCartProduct.getAmount())
                    .price(userCartProduct.getPrice())
                    .build();

            if(userCartProduct.getOption()!=null)
                order.setOption(userCartProduct.getOption().getOption());

            orderRepository.save(order);
        }

        cartRepository.deleteByUser(user.get());
        return ResponseEntity.status(200).body("구매 완료되었습니다.");
    }


//    public void checkProductAndToken(String productName, String token){
//        Optional<Product> product = productRepository.findByProductName(productName);
//        String email = jwtTokenProvider.findEmailBytoken(token);
//        Optional<User> user = userRepository.findByEmail(email);
//
//        if(product.isEmpty())
//            throw new NotFoundException("상품명을 확인해주세요.");
//
//        if(email==null || user.isEmpty())
//            throw new NotFoundException("로그인을 다시 해주세요.");
//    }
}
