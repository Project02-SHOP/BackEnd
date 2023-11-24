package com.b2.prj02.controller.cart;

import com.b2.prj02.dto.cart.CartDTO;
import com.b2.prj02.dto.cart.RequestedCartItemDTO;
import com.b2.prj02.entity.User;
import com.b2.prj02.exception.cart.CartException;
import com.b2.prj02.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping(value = "/cart")
    public ResponseEntity<?> insertCart(@RequestBody RequestedCartItemDTO cartItem, @AuthenticationPrincipal User userDetails
    ) {
        CartDTO cart;
        try {
            Long userId = userDetails.getUserId();
            cart = cartService.addCart(cartItem, userId);
            return ResponseEntity.ok().body(cart);
        } catch (CartException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }






    //2. 장바구니 수정 (수량, 옵션 변경)


    //3. 장바구니 상품 삭제


}
