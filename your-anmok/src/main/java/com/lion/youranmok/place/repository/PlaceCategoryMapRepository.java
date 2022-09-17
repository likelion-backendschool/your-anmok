package com.lion.youranmok.place.repository;

import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.place.entity.PlaceCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceCategoryMapRepository extends JpaRepository<PlaceCategoryMap, Integer> {
    @Query("select distinct pcm.categoryId from PlaceCategoryMap pcm where pcm.placeId = :id")
    List<Integer> getDistinctCategoryIdByPlaceId(Integer id);
}
