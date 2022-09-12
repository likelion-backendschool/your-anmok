package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    Page<CategorySortingDto> findByTagNameContaining(int page, String keyword);

    Page<CategorySortingDto> getListByPaging(int page);

    List<CategorySortingDto> getRecommendCategories();

    List<CategoryDto> getBookmarkCategoriesByUser(int userId);

    Page<CategorySortingDto> getCategories(int page, String keyword);


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
