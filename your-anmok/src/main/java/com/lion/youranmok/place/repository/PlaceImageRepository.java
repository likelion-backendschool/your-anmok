package com.lion.youranmok.place.repository;

import com.lion.youranmok.place.dto.PlaceTagDto;
import com.lion.youranmok.place.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceImageRepository extends JpaRepository<PlaceImage, Integer> {
    List<PlaceImage> getPlaceImagesByPlaceId(Integer id);
}
