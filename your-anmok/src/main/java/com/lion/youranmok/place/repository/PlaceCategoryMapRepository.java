package com.lion.youranmok.place.repository;

import com.lion.youranmok.place.entity.PlaceCategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceCategoryMapRepository extends JpaRepository<PlaceCategoryMap, Integer> {

}
