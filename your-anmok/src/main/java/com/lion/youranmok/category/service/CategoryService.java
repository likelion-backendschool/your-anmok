package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    List<CategoryDto> findByTagNameContaining(String keyword);

    default Category dtoToEntity(CategoryDto dto) {

        Category entity = Category.builder()
                .id(dto.getId())
                .tagName(dto.getTagName())
                .build();

        return entity;

    }

    default CategoryDto entityToDto(Category entity) {

        CategoryDto dto = CategoryDto.builder()
                .id(entity.getId())
                .tagName(entity.getTagName())
                .build();

        return dto;

    }

}
