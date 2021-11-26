package com.meet.app.service;

import com.meet.app.dto.CategoryDTO;
import com.meet.app.entity.Category;
import com.meet.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    // 카테고리 추가
    @Override
    public void categoryRegister(CategoryDTO categoryDTO) {

        Category category = dtoToEntity(categoryDTO);

        categoryRepository.save(category);
    }

    // 카테고리 불러오기
    @Override
    public CategoryDTO categoryRead(Long id) {

        Optional<Category> result = categoryRepository.findById(id);

        Category category = result.get();

        CategoryDTO categoryDTO = entityToDTO(category);

        return categoryDTO;
    }

    @Override
    public void categoryCount(Long id) {

        categoryRepository.plusCount(id);
    }
}
