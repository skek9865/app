package com.meet.app.service;

import com.meet.app.dto.CategoryDTO;
import com.meet.app.entity.Category;

public interface CategoryService {

    void categoryRegister(CategoryDTO categoryDTO);

    CategoryDTO categoryRead(Long id);

    void categoryCount(Long id);

    default Category dtoToEntity(CategoryDTO dto){

        Category category = Category.builder()
                .categoryName(dto.getCategoryName())
                .build();

        return category;
    }

    default CategoryDTO entityToDTO(Category entity){

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .categoryID(entity.getId())
                .categoryName(entity.getCategoryName())
                .build();

        return categoryDTO;
    }
}
