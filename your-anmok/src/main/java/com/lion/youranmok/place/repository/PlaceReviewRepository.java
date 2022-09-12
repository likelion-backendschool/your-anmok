package com.lion.youranmok.place.repository;

import com.lion.youranmok.place.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long> {
    List<PlaceReview> getAllByPlaceId(Integer id);


}