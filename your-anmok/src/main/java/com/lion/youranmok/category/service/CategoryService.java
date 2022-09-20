package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> findAll();
    Page<CategorySortingDto> findByTagNameContaining(int page, String keyword, int userId);

    Page<CategorySortingDto> getListByPaging(int page, int userId);

    List<CategorySortingDto> getRecommendCategories(int userId);

    List<CategoryDto> getBookmarkCategoriesByUser(int userId);

    Page<CategorySortingDto> getCategories(int page, String keyword, int userId);

    Integer addCategory(CategoryDto categoryDto);

    Optional<Category> findByTagName(String tagName);

    Category getCategoryById(Integer id);

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

    Category getCategoryByTagName(String tagName);

}
