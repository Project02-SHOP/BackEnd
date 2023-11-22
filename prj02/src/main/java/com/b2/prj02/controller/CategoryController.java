package com.b2.prj02.controller;

import com.b2.prj02.dto.Category.CategoryDTO;
import com.b2.prj02.repository.CategoryRepository;
import com.b2.prj02.service.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
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
