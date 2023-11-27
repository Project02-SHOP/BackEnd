package com.b2.prj02.shop.option.controller;

import com.b2.prj02.shop.option.dto.DeleteOptionDTO;
import com.b2.prj02.shop.option.dto.OptionDTO;
import com.b2.prj02.shop.option.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/option")
public class OptionController {
    private final OptionService optionService;
    @Transactional
    @PostMapping("/add")
    public ResponseEntity<?> addOptionToProduct(@RequestBody OptionDTO optionDTO,
                                         @RequestHeader("X-AUTH-TOKEN") String token){
        return optionService.addOptionToProduct(optionDTO, token);
    }

    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOptionToProduct(@RequestBody DeleteOptionDTO deleteOptionDTO,
                                            @RequestHeader("X-AUTH-TOKEN") String token){
        return optionService.deleteOptionToProduct(deleteOptionDTO, token);
    }

    @GetMapping()
    public ResponseEntity<?> findAllOptionToProduct(@RequestParam("productId") Long productId){
        return optionService.findAllOptionToProduct(productId);
    }

}
