package com.lion.youranmok.place.repository;

import com.lion.youranmok.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer>  {
    Optional<Place> findPlaceByNameAndAddress(String placename, String address);
}

