package com.lion.youranmok.gathering.repository;

import com.lion.youranmok.gathering.dto.*;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatheringRepository extends JpaRepository<GatheringBoard, Integer>, GatheringRepositoryCustom {


    List<GatheringListDetailDto> listByCriteria();

    @Query("select new com.lion.youranmok.gathering.dto.GatheringDetailDto (" +
            "gb.title," +
            "u.nickname, " +
            "gb.createdAt," +
            "gb.text, " +
            "gb.totalCnt, " +
            "gb.gatherCnt, " +
            "gb.date, " +
            "p.id, " +
            "p.name, " +
            "p.address, " +
            "p.star," +
            "p.lat," +
            "p.lon " +
            ") from GatheringBoard as gb inner join Place as p on p.id = gb.place.id inner join User as u on u.id = gb.userId where gb.id = ?1")
    GatheringDetailDto getDetailById(int id);

    @Query("select new com.lion.youranmok.gathering.dto.GatheringPreviewDto (gb.id, gb.title, gb.date, p.name) " +
            "from GatheringBoard as gb inner join Place as p on p.id = gb.place.id")
    List<GatheringPreviewDto> getPreview();

    @Query("select new com.lion.youranmok.gathering.dto.GatheringListDetailDto(" +
            "gb.id, " +
            "p.name, " +
            "gb.title, " +
            "gb.date, " +
            "gb.totalCnt, " +
            "gb.gatherCnt"+
            ") from GatheringBoard gb inner join Place as p on gb.place.id = p.id where gb.userId = :userId")
    List<GatheringListDetailDto> getDetailByUserId(int userId);

    @Query("select new com.lion.youranmok.gathering.dto.CreateSearchDto (" +
            "p.id," +
            "p.name" +
            ") from Place as p where p.address like %:keyword% or p.name like %:keyword%")
    List<CreateSearchDto> findCreateSearchResultByKeyword(String keyword);
}
