package com.b2.prj02.controller;

import com.b2.prj02.dto.Cart.CartDTO;
import com.b2.prj02.dto.Option.DeleteOptionDTO;
import com.b2.prj02.dto.Option.OptionDTO;
import com.b2.prj02.service.Cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartDetail")
public class CartDetailController {
//    장바구니에 상품 추가 * 제거
//    장바구니 생성 - user
//    장바구니의 상품 구매 - 수량 조정
    private final CartService cartService;
    @PostMapping("/{cartId}/add")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDTO cartDTO,
                                         @RequestHeader("X-AUTH-TOKEN") String token){
        return cartService.addProductToCart(cartDTO, token);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOptionToProduct(@RequestBody DeleteOptionDTO deleteOptionDTO,
                                            @RequestHeader("X-AUTH-TOKEN") String token){
        return optionService.deleteOptionToProduct(deleteOptionDTO, token);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<?> findAllOptionToProduct(@PathVariable String productName){
        return optionService.findAllOptionToProduct(productName);
    }

}
