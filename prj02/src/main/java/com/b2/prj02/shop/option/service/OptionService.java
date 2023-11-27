package com.b2.prj02.shop.option.service;

import com.b2.prj02.config.security.jwt.JwtTokenProvider;
import com.b2.prj02.shop.option.dto.DeleteOptionDTO;
import com.b2.prj02.shop.option.dto.OptionDTO;
import com.b2.prj02.shop.option.entity.Option;
import com.b2.prj02.shop.option.repository.OptionRepository;
import com.b2.prj02.shop.product.entity.Product;
import com.b2.prj02.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OptionService {
    private final ProductRepository productRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OptionRepository optionRepository;
    public ResponseEntity<?> addOptionToProduct(OptionDTO optionDTO, String token) {
        Optional<Product> product = productRepository.findByProductId(optionDTO.getProductId());

        if(product.isEmpty())
            throw new DisabledException("없는 상품입니다.");

        if(token==null || jwtTokenProvider.findEmailBytoken(token)==null)
            throw new DisabledException("없는 유저입니다.");

        if(!jwtTokenProvider.findEmailBytoken(token).equals(product.get().getUser().getEmail()))
            throw new DisabledException("다른 유저의 상품입니다.");

        Option newOption = Option.builder()
                .product(product.get())
                .option(optionDTO.getOption())
                .optionPrice(optionDTO.getOptionPrice())
                .build();

        optionRepository.save(newOption);
        return ResponseEntity.status(200).body(product.get().getProductName() + " 상품에 " + optionDTO.getOption() + " 옵션이 추가되었습니다.");
    }

    public ResponseEntity<?> deleteOptionToProduct(DeleteOptionDTO deleteOptionDTO, String token) {
        Optional<Product> product = productRepository.findByProductId(deleteOptionDTO.getProductId());

        if(product.isEmpty())
            throw new DisabledException("없는 상품입니다.");

        if(token==null || jwtTokenProvider.findEmailBytoken(token)==null)
            throw new DisabledException("없는 유저입니다.");

        if(!jwtTokenProvider.findEmailBytoken(token).equals(product.get().getUser().getEmail()))
            throw new DisabledException("다른 유저의 상품입니다.");

        optionRepository.deleteByOption(deleteOptionDTO.getOption());
        return ResponseEntity.status(200).body(product.get().getProductName() + " 상품에 " + deleteOptionDTO.getOption() + " 옵션이 제거되었습니다.");
    }

    public ResponseEntity<?> findAllOptionToProduct(Long productId) {
        Optional<Product> product = productRepository.findByProductId(productId);

        if(product.isEmpty())
            throw new DisabledException("없는 상품입니다.");

        if(optionRepository.findByProduct(product.get()).isEmpty())
            return ResponseEntity.status(200).body(product.get().getProductName() + "상품에는 옵션이 없습니다.");

        return ResponseEntity.status(200).body(optionRepository.findByProduct(product.get()));
    }
}
