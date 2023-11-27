package com.b2.prj02.option.controller;

import com.b2.prj02.option.dto.DeleteOptionDTO;
import com.b2.prj02.option.dto.OptionDTO;
import com.b2.prj02.option.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/option")
public class OptionController {
    private final OptionService optionService;
    @PostMapping("/add")
    public ResponseEntity<?> addOptionToProduct(@RequestBody OptionDTO optionDTO,
                                         @RequestHeader("X-AUTH-TOKEN") String token){
        return optionService.addOptionToProduct(optionDTO, token);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOptionToProduct(@RequestBody DeleteOptionDTO deleteOptionDTO,
                                            @RequestHeader("X-AUTH-TOKEN") String token){
        return optionService.deleteOptionToProduct(deleteOptionDTO, token);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<?> findAllOptionToProduct(@PathVariable String productName){
        return optionService.findAllOptionToProduct(productName);
    }

}
