package com.lion.youranmok.place.repository;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.gathering.dto.GatheringPreviewDto;
import com.lion.youranmok.place.dto.PlaceGatheringDto;
import com.lion.youranmok.place.dto.PlaceTagDto;
import com.lion.youranmok.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer>  {
    @Query("select new com.lion.youranmok.place.dto.PlaceTagDto (p.id, c.tagName)" +
            "from Category as c inner join Place p on p.id = c.placeId")
    List<PlaceTagDto> getTagNameById(int id);

    @Query("select new com.lion.youranmok.place.dto.PlaceGatheringDto (gb.id, p.id, gb.title)" +
            "from GatheringBoard as gb inner join Place p on p.id = gb.place.id order by gb.createdAt desc")
    List<PlaceGatheringDto> getGatheringListByPlaceId(Integer id);

    Optional<Place> findPlaceByNameAndAddress(String placename, String address);

}