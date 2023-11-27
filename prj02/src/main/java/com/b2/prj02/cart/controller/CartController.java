package com.b2.prj02.cart.controller;

import com.b2.prj02.cart.dto.CartDTO;
import com.b2.prj02.cart.dto.DeleteCartDTO;
import com.b2.prj02.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDTO cartDTO,
                                         @RequestHeader("X-AUTH-TOKEN") String token){
        return cartService.addProductToCart(cartDTO, token);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductToCart(@RequestBody DeleteCartDTO deleteCartDTO,
                                            @RequestHeader("X-AUTH-TOKEN") String token){
        return cartService.deleteProductToCart(deleteCartDTO, token);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProductToCart(@RequestBody CartDTO cartDTO,
                                                 @RequestHeader("X-AUTH-TOKEN") String token){
        return cartService.updateProductToCart(cartDTO, token);
    }

    @GetMapping("/Detail")
    public ResponseEntity<?> findAllMyCartDetail(@RequestHeader("X-AUTH-TOKEN") String token){
        return cartService.findAllMyCartDetail(token);
    }

    @GetMapping()
    public ResponseEntity<?> findAllMyCart(@RequestHeader("X-AUTH-TOKEN") String token){
        return cartService.findAllMyCart(token);
    }

    @Transactional
    @PostMapping("/payment")
    public ResponseEntity<?> payMyCart(@RequestHeader("X-AUTH-TOKEN") String token){
        return cartService.payMyCart(token);
    }
}
