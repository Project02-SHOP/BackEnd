package com.b2.prj02.service.Cart;

import com.b2.prj02.dto.Cart.CartDTO;
import com.b2.prj02.dto.Category.CategoryDTO;
import com.b2.prj02.entity.*;
import com.b2.prj02.exception.NotFoundException;
import com.b2.prj02.repository.*;
import com.b2.prj02.service.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<?> addProductToCart(CartDTO cartDTO, String token) {
        Optional<Product> product = productRepository.findByProductName(cartDTO.getProductName());
        String email = jwtTokenProvider.findEmailBytoken(token);
        Optional<User> user = userRepository.findByEmail(email);

        if(product.isEmpty())
            throw new NotFoundException("상품명을 확인해주세요.");

        if(email==null || user.isEmpty())
            throw new NotFoundException("로그인을 다시 해주세요.");

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

                newProduct.setOption(option.get());
                newProduct.addOptionPrice(option.get().getOptionPrice());
            }

            cartDetailRepository.save(newProduct);
        }
        return ResponseEntity.status(200).body("상품이 성공적으로 추가되었습니다.");
    }

    public void addCart(User user){
        Cart newCart = Cart.builder()
                            .user(user)
                            .build();

        cartRepository.save(newCart);
    }
}
