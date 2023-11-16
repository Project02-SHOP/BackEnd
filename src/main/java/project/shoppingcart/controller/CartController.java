package project.shoppingcart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.shoppingcart.DTO.CartDTO;
import project.shoppingcart.service.CartService;

@Controller
@RequestMapping("api/cart")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService cartService) {
        this.service = cartService;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<CartDTO> addToCart(@RequestBody CartDTO cartItem){
        // DTO를 서비스로 전달하고 처리된 결과를 받음
        CartDTO addedCartItemDTO = service.addToCart(cartItem);

        return new ResponseEntity<>(addedCartItemDTO, HttpStatus.CREATED);
    }
}