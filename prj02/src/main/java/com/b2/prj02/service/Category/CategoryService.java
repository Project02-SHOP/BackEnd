package com.b2.prj02.service.Category;

import com.b2.prj02.dto.CategoryDTO;
import com.b2.prj02.entity.Category;
import com.b2.prj02.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
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
}
