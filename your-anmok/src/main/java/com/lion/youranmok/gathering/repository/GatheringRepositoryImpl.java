package com.lion.youranmok.gathering.repository;

import com.lion.youranmok.gathering.dto.GatheringListDetailDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class GatheringRepositoryImpl implements GatheringRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<GatheringListDetailDto> listByCriteria() {
//        String baseCountQuery = "select count(gb) from GatheringBoard as gb where gb.isExpired = false;";
        // 레코드 조회
        // String baseQuery = "select cs from Cs left join  ";
        String baseQuery = "select new com.lion.youranmok.gathering.dto.GatheringListDetailDto(" +
                "gb.id, " +
                "p.name, " +
                "gb.title, " +
                "gb.date, " +
                "gb.totalCnt, " +
                "gb.gatherCnt"+
                ") from GatheringBoard gb inner join Place as p on gb.placeId = p.id";

        StringBuilder countBuilderWhere = new StringBuilder();
        StringBuilder objectBuilderWhere = new StringBuilder();
//        String whereQuery = " where 0 = 0 ";
//        countBuilderWhere.append(whereQuery);
//        objectBuilderWhere.append(whereQuery);

        //count query
//        Query countQ = entityManager.createQuery(
//                baseCountQuery + countBuilderWhere.toString());

        //object query
        Query objectQ = entityManager.createQuery(
                baseQuery );
//        + objectBuilderWhere.toString()
        return objectQ.getResultList();
    }
}
