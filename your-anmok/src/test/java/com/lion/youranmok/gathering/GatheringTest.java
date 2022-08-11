package com.lion.youranmok.gathering;

import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.repository.CategoryRepository;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class GatheringTest {

    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void insertGatheringBoardTest() {
        //번개모임 정보 세팅
        for(int i = 0; i < 50; i++) {
            GatheringBoard gatheringBoard = new GatheringBoard();

            gatheringBoard.setId(i+1);
            gatheringBoard.setCreatedAt(LocalDateTime.now());
            gatheringBoard.setModifiedAt(LocalDateTime.now());
            gatheringBoard.setPlaceId(1);
            gatheringBoard.setUserId(1);
            gatheringBoard.setGatherCnt(5);
            gatheringBoard.setTotalCnt(10);
            gatheringBoard.setDate(LocalDate.now());
            gatheringBoard.setTitle("빵이 맛있는 집 "+(i+1)+"호에서 만나요🥨");
            gatheringBoard.setText("글.. 작성중.. ");
            gatheringBoard.setIsExpired(false);

            gatheringRepository.save(gatheringBoard);
        }
    }

    @Test
    public void insertCategoryTest() {
        //번개모임 정보 세팅 - 카테고리 버전
        //카테고리 정보 세팅(test data)
        List<String> nameList = Arrays.asList("#코딩하기 좋은 카페💻", "#혼자 맥주한잔 하고싶을때🍺", "#노을맛집🌅", "#보기만해도 마음이 편해지는 바다🌊",
                "#담배피기 좋은 장소🚬", "#오늘은 차박🚘", "#소개팅으로 좋은 맛집🔥");

        for(int i = 0; i < nameList.size(); i++) {
            Category category = new Category();
            category.setTagName(nameList.get(i));
            category.setPlaceId(1);

            categoryRepository.save(category);
        }
    }

    @Test
    public void GatheringRepositoryTest() {
        List<GatheringBoard> gatheringBoardList = gatheringRepository.listByExpiredFalse();

        System.out.println(gatheringBoardList.toString());
    }

}
