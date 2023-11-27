package com.b2.prj02.service.Category;

import com.b2.prj02.dto.Category.CategoryDTO;
import com.b2.prj02.entity.Category;
import com.b2.prj02.exception.NotFoundException;
import com.b2.prj02.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public ResponseEntity<?> addCategory(CategoryDTO categoryDTO) {
        Category newCategory = Category.builder()
                                        .categoryName(categoryDTO.getCategoryName())
                                        .build();

        categoryRepository.save(newCategory);
        return ResponseEntity.status(200).body(categoryDTO.getCategoryName() + "가 카테고리로 추가되었습니다.");
    }

    public ResponseEntity<?> deleteCategory(CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if(category.isPresent()){
            categoryRepository.delete(category.get());
            return ResponseEntity.status(200).body(categoryDTO.getCategoryName() + "이 정상적으로 삭제되었습니다.");
        }
        else {
            throw new NotFoundException("없는 카테고리입니다.");
        }
    }
}
