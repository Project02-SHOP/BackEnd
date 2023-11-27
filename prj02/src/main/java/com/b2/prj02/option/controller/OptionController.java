package com.b2.prj02.controller;

import com.b2.prj02.dto.Option.DeleteOptionDTO;
import com.b2.prj02.dto.Option.OptionDTO;
import com.b2.prj02.service.Option.OptionService;
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
