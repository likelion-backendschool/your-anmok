package com.lion.youranmok.category.dto.repository;

import com.lion.youranmok.category.dto.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
