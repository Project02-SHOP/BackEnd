package com.b2.prj02.service.Product;

import com.b2.prj02.dto.Product.DeleteProductDTO;
import com.b2.prj02.dto.Product.ProductDTO;
import com.b2.prj02.entity.Category;
import com.b2.prj02.entity.Product;
import com.b2.prj02.exception.NotFoundException;
import com.b2.prj02.repository.CategoryRepository;
import com.b2.prj02.repository.ProductRepository;
import com.b2.prj02.repository.UserRepository;
import com.b2.prj02.service.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
            throw new NotFoundException("없는 카테고리입니다.");

        if(token.isEmpty()||email.isEmpty())
            throw new NotFoundException("없는 유저입니다.");


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
            throw new NotFoundException("상품명을 확인해주세요.");

        if(email==null)
            throw new NotFoundException("로그인을 다시 해주세요.");

        if(!email.equals(product.get().getUser().getEmail()))
            throw new NotFoundException("다른 유저의 상품입니다.");

        productRepository.deleteByProductName(deleteProductDTO.getProductName());
        return ResponseEntity.status(200).body(deleteProductDTO.getProductName() + "가 정상적으로 삭제되었습니다.");
    }


}
