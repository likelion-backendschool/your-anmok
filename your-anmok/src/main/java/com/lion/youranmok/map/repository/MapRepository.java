package com.lion.youranmok.map.repository;

import com.lion.youranmok.map.dto.MapDto;
import com.lion.youranmok.map.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map, Integer> {

    List<Map> findByCategoryId(int id);
}
