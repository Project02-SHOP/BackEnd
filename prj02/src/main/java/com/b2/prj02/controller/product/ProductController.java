package com.b2.prj02.controller.product;

import com.b2.prj02.dto.product.ProductDTO;
import com.b2.prj02.dto.request.ProductListRequest;
import com.b2.prj02.exception.product.ProductNotFoundException;
import com.b2.prj02.service.product.PaginationResponse;
import com.b2.prj02.service.product.ProductListResponse;
import com.b2.prj02.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //상품 전체 조회
    @GetMapping(value = "/")
    public ResponseEntity<PaginationResponse<ProductListResponse>> getProductList(ProductListRequest productListRequest,
                                            @RequestParam(required = false) List<String> category,
                                            Pageable pageable) {
        productListRequest.setCategory(category);
        PaginationResponse<ProductListResponse> productListResponse = productService.getProductList(productListRequest, pageable);

        return ResponseEntity.ok(productListResponse);
    }
    // api/shop 엔드포인트로 들어오는 요청에 대해 페이지네이션을 적용하도록 변경
    // 페이지네이션 처리는 Pageable 객체를 이용하여 Spring Data JPA에서 자동으로 처리


    //상품 상세 조회
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        try {
            ProductDTO productDTO = productService.getProductById(productId);
            return ResponseEntity.ok().body(productDTO);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}
