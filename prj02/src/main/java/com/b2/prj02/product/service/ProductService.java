package com.b2.prj02.product.service;

import com.b2.prj02.category.entity.Category;
import com.b2.prj02.category.repository.CategoryRepository;
import com.b2.prj02.config.security.jwt.JwtTokenProvider;
import com.b2.prj02.product.dto.DeleteProductDTO;
import com.b2.prj02.product.dto.ProductDTO;
import com.b2.prj02.product.entity.Product;
import com.b2.prj02.product.repository.ProductRepository;
import com.b2.prj02.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public ResponseEntity<?> addProduct(ProductDTO productDTO, String token) {
        Optional<Category> category = categoryRepository.findByCategoryName(productDTO.getCategory());
        String email = jwtTokenProvider.findEmailBytoken(token);


        if(category.isEmpty())
            throw new DisabledException("없는 카테고리입니다.");

        if(token.isEmpty()||email.isEmpty())
            throw new DisabledException("없는 유저입니다.");


        Product newProduct = Product.builder()
                                    .productName(productDTO.getProductName())
                                    .price(productDTO.getPrice())
                                    .productQuantity(productDTO.getProductQuantity())
                                    .category(category.get())
//                .registerDate()
//                .saleEndDate()
                                    .user(userRepository.findByEmail(email).get())
                                    .build();

        productRepository.save(newProduct);
        return ResponseEntity.status(200).body(productDTO.getProductName() + "이 정상적으로 등록되었습니다.");
    }

    public ResponseEntity<?> deleteProduct(DeleteProductDTO deleteProductDTO, String token) {
        Optional<Product> product = productRepository.findByProductName(deleteProductDTO.getProductName());
        String email = jwtTokenProvider.findEmailBytoken(token);
        if(product.isEmpty())
            throw new DisabledException("상품명을 확인해주세요.");

        if(email==null)
            throw new DisabledException("로그인을 다시 해주세요.");

        if(!email.equals(product.get().getUser().getEmail()))
            throw new DisabledException("다른 유저의 상품입니다.");

        productRepository.deleteByProductName(deleteProductDTO.getProductName());
        return ResponseEntity.status(200).body(deleteProductDTO.getProductName() + "가 정상적으로 삭제되었습니다.");
    }


}