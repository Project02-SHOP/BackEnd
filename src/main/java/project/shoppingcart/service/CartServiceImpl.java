package project.shoppingcart.service;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import project.shoppingcart.DTO.CartDTO;
import project.shoppingcart.entity.CartEntity;

import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public CartDTO addItemToCart(CartEntity cartEntity, int quantity, Map<String, String> options) {
        // 여기에서 필요한 로직 수행

        // 엔터티를 DTO로 변환하여 반환
        return convertToDTO(cartEntity);
    }

    @Override
    public Object viewCart() {
        // 여기에서 장바구니 조회에 필요한 비즈니스 로직 수행

        // 임시로 null 반환
        return null;
    }

    @Override
    public CartDTO addToCart(CartEntity cartItem) {
        // 여기에서 엔터티를 처리하고 저장에 필요한 비즈니스 로직 수행

        // 예시로 엔터티를 DTO로 변환하여 반환
        return convertToDTO(cartItem);
    }

    // DTO를 엔터티로 변환하는 메서드
    private CartEntity convertToEntity(CartDTO cartDTO) {
        CartEntity cartEntity = new CartEntity();
        BeanUtils.copyProperties(cartDTO, cartEntity);
        return cartEntity;
    }

    // 엔터티를 DTO로 변환하는 메서드
    private CartDTO convertToDTO(CartEntity cartEntity) {
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(cartEntity, cartDTO);
        return cartDTO;
    }
}

