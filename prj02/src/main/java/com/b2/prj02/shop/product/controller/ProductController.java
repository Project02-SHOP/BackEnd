package com.b2.prj02.shop.product.controller;

import com.b2.prj02.shop.product.dto.DeleteProductDTO;
import com.b2.prj02.shop.product.dto.ProductDTO;
import com.b2.prj02.shop.product.repository.ProductRepository;
import com.b2.prj02.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    //    물품 등록 * 제거 * 조회
    private final ProductService productService;
    private final ProductRepository productRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO,
                                         @RequestHeader("X-AUTH-TOKEN") String token){
        return productService.addProduct(productDTO, token);
    }

    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody DeleteProductDTO deleteProductDTO,
                                            @RequestHeader("X-AUTH-TOKEN") String token){
        return productService.deleteProduct(deleteProductDTO, token);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllProduct(){
        return ResponseEntity.status(200).body(productRepository.findAll());
    }

    @GetMapping()
    public ResponseEntity<?> findByProductName(@RequestParam("productName") String productName){
        return ResponseEntity.status(200).body(productRepository.findByProductName(productName));
    }
}
