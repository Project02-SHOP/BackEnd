package com.b2.prj02.shop.category.controller;

import com.b2.prj02.shop.category.dto.CategoryDTO;
import com.b2.prj02.shop.category.repository.CategoryRepository;
import com.b2.prj02.shop.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO,
                                         @RequestHeader("X-AUTH-TOKEN") String token){
        return categoryService.addCategory(categoryDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestBody CategoryDTO categoryDTO,
                                            @RequestHeader("X-AUTH-TOKEN") String token){
        return categoryService.deleteCategory(categoryDTO);
    }

    @GetMapping()
    public ResponseEntity<?> findAllCategory(){
        return ResponseEntity.status(200).body(categoryRepository.findAll());
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<?> findByCategoryName(@PathVariable String categoryName){
        return ResponseEntity.status(200).body(categoryRepository.findByCategoryName(categoryName));
    }
}
