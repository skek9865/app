package com.meet.app.service;

import com.meet.app.dto.CategoryDTO;
import com.meet.app.entity.Category;
import com.meet.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    // 카테고리 추가
    @Override
    public void register(CategoryDTO categoryDTO) {
        log.info("CategoryService - register");
        log.info("categoryDTO : " + categoryDTO);

        Category category = dtoToEntity(categoryDTO);

        categoryRepository.save(category);
    }

    // 카테고리 불러오기
    @Override
    public CategoryDTO getOne(Long id) {
        log.info("CategoryService - getOne");
        log.info("id : " + id);

        Optional<Category> result = categoryRepository.findById(id);

        Category category = result.get();

        CategoryDTO categoryDTO = entityToDTO(category);

        return categoryDTO;
    }

    @Override
    public void categoryCount(Long id) {
        log.info("CategoryService - categoryCount");
        log.info("id : " + id);

        categoryRepository.plusCount(id);
    }
}
