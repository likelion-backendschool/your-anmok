package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    List<CategoryDto> findByTitleContaining(String keyword);

    default Category dtoToEntity(CategoryDto dto) {

        Category entity = Category.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .build();

        return entity;

    }

    default CategoryDto entityToDto(Category entity) {

        CategoryDto dto = CategoryDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();

        return dto;

    }

}
