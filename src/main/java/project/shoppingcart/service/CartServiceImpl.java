package project.shoppingcart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.shoppingcart.DTO.CartDTO;
import project.shoppingcart.entity.CartEntity;
import project.shoppingcart.repository.CartRepository;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }


    @Override
    public CartDTO addItemToCart(CartDTO cartDTO, int quantity, Map<String, String> options){
        // 여기에서 필요한 비즈니스 로직 수행

        // 엔터티로 변환후 저장
        CartEntity cartEntity = convertToEntity(cartDTO);
        CartEntity addedCartItem = cartRepository.save(cartEntity);

        // 결과를 다시 DTO로 변환하여 반환
        return convertToDTO(addedCartItem);
    }


    @Override
    public CartDTO addItemToCart(CartEntity cartEntity, int quantity, Map<String, String> options) {
        return null;
    }

    @Override
    public Object viewCart() {
        // 여기에서 장바구니 조회에 필요한 비즈니스 로직 수행

        // 이름으로 장바구니 검색
        List<CartEntity> carts = cartRepository.findByName("");

        // 검색된 결과를 반환하거나 다른 비즈니스로 로직을 수행
        return carts;
    }


    @Override
    public CartDTO addToCart(CartEntity cartItem){
        // 여기에서 엔터티를 처리하고 저장에 필요한 비즈니스 로직 수행
        // 예시로 엔터티를 그대로 반환하도록 구현
        return cartItem;
    }


    // DTO를 엔터티로 변환하는 메서드
    private CartEntity convertToEntity(CartDTO cartDTO){
        CartEntity cartEntity = new CartEntity();
        // DTO의 필드를 엔터티에 복사
        // 필요에 따라 더 많은 복사 로직 추가
        cartEntity.setName(cartDTO.getName());
        cartEntity.setPrice(cartDTO.getPrice());
        return cartEntity;
    }


    // 엔터티를 DTO로 변환하는 메서드
    private CartDTO convertToDTO(CartEntity cartEntity){
        CartDTO cartDTO = new CartDTO();
        // 엔터티의 필드를 DTO에 복사
        // 필요에 따라 더 많은 복사 로직 추가
        cartDTO.setName(cartEntity.getName());
        cartDTO.setPrice(cartEntity.getPrice());
        return cartDTO;
    }
}

