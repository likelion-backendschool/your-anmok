package com.lion.youranmok.category.repository;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByTagNameContaining(String keyword);

}
