package com.lion.youranmok.gathering.repository;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.entity.GatheringListCriteria;
import com.lion.youranmok.place.entity.QPlaceCategoryMap;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static com.lion.youranmok.category.entity.QCategory.category;
import static com.lion.youranmok.gathering.entity.QGatheringBoard.gatheringBoard;
import static com.lion.youranmok.place.entity.QPlace.place;
import static com.lion.youranmok.place.entity.QPlaceCategoryMap.placeCategoryMap;

@RequiredArgsConstructor
public class GatheringRepositoryImpl implements GatheringRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GatheringListDetailDto> listByCriteria(GatheringListCriteria criteria) {


//        String baseQuery = "select new com.lion.youranmok.gathering.dto.GatheringListDetailDto(" +
//                "gb.id, " +
//                "p.name, " +
//                "gb.title, " +
//                "gb.date, " +
//                "gb.totalCnt, " +
//                "gb.gatherCnt"+
//                ") from GatheringBoard gb inner join Place as p on gb.place.id = p.id inner join PlaceCategoryMap as pcm on pcm.placeId = p.id";

        return queryFactory
                .select(Projections.constructor(GatheringListDetailDto.class, gatheringBoard.id, place.name, gatheringBoard.title, gatheringBoard.date, gatheringBoard.totalCnt, gatheringBoard.gatherCnt))
                .distinct()
                .from(gatheringBoard)
                .innerJoin(placeCategoryMap).on(placeCategoryMap.placeId.eq(gatheringBoard.place.id))
                .innerJoin(place).on(place.id.eq(placeCategoryMap.placeId))
                .where(inCategories(criteria.getCategory()),
                        keywordLike(criteria.getClassification(), criteria.getSearchKeyword()))
                .fetch();


    }

    private BooleanExpression inCategories(List<Integer> categories) {
        if(categories == null || categories.size() == 0) {
            return null;
        }
        return placeCategoryMap.categoryId.in(categories);
    }

    private BooleanExpression keywordLike(String classification, String keyword) {
        if(classification == null || keyword == null || classification == "" || keyword == "") {
            return null;
        }

        if(classification.equals("address")) {
            System.out.println("Address");
            return place.address.contains(keyword);
        } else if(classification.equals("name")) {
            System.out.println("name");
            return place.name.contains(keyword);
        } else {
            return null;
        }
    }
}
