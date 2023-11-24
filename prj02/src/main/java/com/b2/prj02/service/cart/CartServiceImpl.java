package com.b2.prj02.service.cart;

import com.b2.prj02.dto.cart.CartDTO;
import com.b2.prj02.dto.cart.RequestedCartItemDTO;
import com.b2.prj02.entity.User;
import com.b2.prj02.entity.cart.CartEntity;
import com.b2.prj02.entity.cart.CartItemEntity;
import com.b2.prj02.entity.product.ProductEntity;
import com.b2.prj02.exception.cart.UserCartNotFoundException;
import com.b2.prj02.exception.product.ProductNotFoundException;
import com.b2.prj02.exception.user.UserNotFoundException;
import com.b2.prj02.repository.UserRepository;
import com.b2.prj02.repository.cart.CartRepository;
import com.b2.prj02.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements  CartService{

    private final UserRepository userRepository; //
    private final ProductRepository productRepository;//
    private final CartRepository cartRepository;



    @Override
    public CartDTO addCart(RequestedCartItemDTO cartItem, Long userId) {
        User cartUser;
        CartItemEntity newItemRequested;

        try {
            // 유저의 카트 유무 확인
            CartEntity userCart = cartRepository.findByUser_UserId(userId)
                    .orElseThrow(() -> new UserCartNotFoundException("해당 회원의 장바구니가 존재하지 않습니다."));

            // 요청된 cartItem의 상품id로 해당상품 찾아오기
            ProductEntity item1 = productRepository.findByProductId(cartItem.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("해당 상품이 존재하지 않습니다."));

            // userId로 User 가져오기.
            cartUser = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("해당 회원이 존재하지 않습니다."));

            newItemRequested = CartItemEntity.requestedItemDTOToEntity(item1, cartItem, userCart);
            userCart.addCartItems(newItemRequested);
            cartRepository.save(userCart);
            CartDTO itemAddedCart = CartDTO.cartEntityToDTO(userCart);

            return itemAddedCart;

        } catch (UserCartNotFoundException e) {
            // 유저의 장바구니가 없으면 새로운 장바구니 생성
            ProductEntity item2 = productRepository.findByProductId(cartItem.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("해당 상품이 존재하지 않습니다."));
            CartEntity newCart = new CartEntity();
            cartUser = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("해당 회원이 존재하지 않습니다."));
            newCart.createCart(cartUser);
            newItemRequested = CartItemEntity.requestedItemDTOToEntity(item2, cartItem, newCart);
//            CartItemEntity newRequestedCartItem = new CartItemEntity(newCart, item2,cartItem.getQuantity(), (long) item2.getPrice(), cartItem.getOption());
            newCart.addCartItems(newItemRequested);
            cartRepository.save(newCart);
            CartDTO itemAddedCart = CartDTO.cartEntityToDTO(newCart);
            return itemAddedCart;
        }

//
//        유저의 카트 유무 확인
//        CartEntity userCart = cartRepository.findByUser_UserId(userId).orElseThrow(()-> new UserCartNotFoundException("해당 회원의 장바구니가 존재하지 않습니다.")); //여기 수정
//
//        //요청된 cartItem의 상품id로 해당상품 찾아오기
//        ProductEntity item = productRepository.findById(cartItem.getProductId()).orElseThrow(()-> new ProductNotFoundException("해당 상품이 존재하지 않습니다."));
//
//        //userId로 User 가지고 오기.
//        User cartUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("해당 회원이 존재하지 않습니다."));
//
//        CartItemEntity newItemRequested = new CartItemEntity();
//
//        //유저의 카트가 있다면,
//        if(userCart != null){
//
//            newItemRequested = CartItemEntity.requestedItemDTOToEntity(item,cartItem,userCart);
//            userCart.addCartItems(newItemRequested);
//            cartRepository.save(userCart);
//            CartDTO itemAddedCart = CartDTO.cartEntityToDTO(userCart);
//
//            return itemAddedCart;
//
//        }//유저의 카트가 없다면,
//        else{
//            //1.상품 장바구니에 담기
//            //1) - 유저의 장바구니가 있으면 가지고 오고, - 유저의 장바구니가 없으면 유저의 장바구니를 만든다.
//
//            //새로운 카트 생성
//            CartEntity newCart  = new CartEntity();
//
//            //새로 만든 카트에 유저 할당.
//            newCart.createCart(cartUser);
//
//            //요청된 상품, 수량, 옵션(cartItem)을 requestedCartItem에 넣어주기(DTO to Entity)
//            //CartItemEntity newRequestedCartItem = new CartItemEntity(newCart, item,cartItem.getQuantity(), (long) item.getPrice(), cartItem.getOption());
//
//            newItemRequested = CartItemEntity.requestedItemDTOToEntity(item,cartItem,newCart);
//
//
//            //CartItemEntity를 유저의 CartEntity에 넣어주기.
//            newCart.addCartItems(newItemRequested);
//
//            //CartItemEntity가 담긴 CartEntity를 Repository에 저장해준다.
//            cartRepository.save(newCart);
//
//            //CartEntity를 CartDTO로 변환해준다.
//            CartDTO itemAddedCart = CartDTO.cartEntityToDTO(newCart);
//
//            return itemAddedCart;
//        }
//
//        //2.장바구니에 담은 장바구니아이템의 수량 및 옵션 수정하기
//        //3.장바구니 아이템 삭제하기
//
//
//
//
//    }
    }
}
