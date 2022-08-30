package com.lion.youranmok.category.repository;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Page<Category> findByTagNameContaining(Pageable pageable, String keyword);

    Page<Category> findAll(Pageable pageable);

    @Query("select new com.lion.youranmok.category.dto.CategorySortingDto (" +
            "c.id," +
            "c.tagName," +
            "(select count(b.id) from Bookmark b where b.categoryId = c.id))" +
            "from Category c")
    List<CategorySortingDto> getSortingCategories();

    @Query("select new com.lion.youranmok.category.dto.CategorySortingDto (" +
            "c.id," +
            "c.tagName," +
            "(select count(b.id) from Bookmark b where b.categoryId = c.id))" +
            "from Category c where c.tagName like %:keyword%")
    List<CategorySortingDto> getSortingCategoriesContainingKeyword(String keyword);


}
