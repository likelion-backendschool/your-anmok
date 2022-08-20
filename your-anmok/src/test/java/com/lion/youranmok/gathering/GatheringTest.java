package com.lion.youranmok.gathering;

import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.repository.CategoryRepository;
import com.lion.youranmok.gathering.dto.CommentDto;
import com.lion.youranmok.gathering.dto.GatheringDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.entity.GatheringComment;
import com.lion.youranmok.gathering.repository.GatheringCommentRepository;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import com.lion.youranmok.map.entity.Place;
import com.lion.youranmok.map.repository.PlaceRepository;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GatheringTest {

    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private GatheringCommentRepository gatheringCommentRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;


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
    public void insertGatheringCommentTest() {
        //번개모임 댓글 세팅
        for(int i = 0; i < 20; i++) {
            GatheringComment gatheringComment = new GatheringComment();

            gatheringComment.setCommentText("%d번째 댓글".formatted(i));
            gatheringComment.setCreatedAt(LocalDateTime.now());
            gatheringComment.setModifiedAt(LocalDateTime.now());
            gatheringComment.setUserId(1);
            gatheringComment.setBoard(gatheringRepository.findById(1).orElse(null));

            gatheringCommentRepository.save(gatheringComment);
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
        List<GatheringListDetailDto> gatheringBoardList = gatheringRepository.listByCriteria();

        System.out.println(gatheringBoardList.toString());
    }


    @Test
    public void insertMapTest() {
        List<String> placeNameList = Arrays.asList("리멤버미 서울대입구역","컴즈커피","비하인드","빌리프커피로스터스","커피덕","플랫랜드","디벙크");

        for(int i = 0; i < placeNameList.size(); i++) {
            Place place = new Place();
            place.setAddress("서울 관악구 관악로14길 70 효림빌딩 4층 %d".formatted(i));
            place.setName(placeNameList.get(i));
            place.setRateCnt(30);
            place.setStar(70);
            place.setLat(33.450701);
            place.setLon(126.570667);

            placeRepository.save(place);
        }

    }

    @Test
    public void insertUserTest() {
        List<String> userNameList = Arrays.asList("생갈치 1호의 행방불명", "오즈의 맙소사","반지하 제왕", "김대희","박다정", "순데렐라", "배숙희라빈스");

        for(int i = 0; i < userNameList.size(); i++) {
            User user = new User();
            user.setNickname(userNameList.get(i));
            user.setProfilePicture("/images/profile/%d.jpeg".formatted(i+1));
            user.setToken("tmp%d".formatted(i));

            userRepository.save(user);
        }
    }

    @Test
    public void gatheringDetailTest() {
        GatheringDetailDto gatheringDetailDto = gatheringRepository.getDetailById(1);
        List<CommentDto> commentList = gatheringCommentRepository.listByBoardId(1);

        System.out.println(gatheringDetailDto.toString());
        System.out.println(commentList.toString());

    }

}
