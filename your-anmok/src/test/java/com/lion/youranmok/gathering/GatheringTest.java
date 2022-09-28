package com.lion.youranmok.gathering;

import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.repository.CategoryRepository;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.repository.GatheringCommentRepository;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import com.lion.youranmok.login.dto.KakaoUserDto;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.entity.PlaceCategoryMap;
import com.lion.youranmok.place.repository.PlaceCategoryMapRepository;
import com.lion.youranmok.place.repository.PlaceRepository;
import com.lion.youranmok.user.repository.UserRepository;
import com.lion.youranmok.user.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("db-prod")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceCategoryMapRepository placeCategoryMapRepository;


    @Test
    @Order(1)
    public void insertCategoryTest() {
        //번개모임 정보 세팅 - 카테고리 버전
        //카테고리 정보 세팅(test data)
        List<String> nameList = Arrays.asList("#코딩하기 좋은 카페💻", "#혼자 맥주한잔 하고싶을때🍺", "#노을맛집🌅", "#보기만해도 마음이 편해지는 바다🌊",
                "#담배피기 좋은 장소🚬", "#오늘은 차박🚘", "#소개팅으로 좋은 맛집🔥");

        for(int i = 0; i < nameList.size(); i++) {
            Category category = new Category();
            category.setTagName(nameList.get(i));

            categoryRepository.save(category);
        }
    }

    @Test
    @Order(2)
    public void insertMapTest() {
        List<String> placeNameList = Arrays.asList("리멤버미 서울대입구역","컴즈커피","비하인드","빌리프커피로스터스","커피덕","플랫랜드","디벙크");

        for(int i = 0; i < placeNameList.size(); i++) {
            Place place = new Place();
            place.setAddress("서울 관악구 관악로14길 70 효림빌딩 4층 %d".formatted(i));
            place.setName(placeNameList.get(i));
            //place.setRateCnt(30);

            place.setStar(4);
            place.setLat(33.450701);
            place.setLon(126.570667);

            placeRepository.save(place);
        }

    }

    @Test
    @Order(3)
    public void insertPlaceCategoryMapTest() {
        List<PlaceCategoryMap> maps = new ArrayList<>();

        PlaceCategoryMap map1 = new PlaceCategoryMap();
        map1.setCategoryId(1);
        map1.setPlaceId(1);
        maps.add(map1);

        PlaceCategoryMap map2 = new PlaceCategoryMap();
        map2.setCategoryId(1);
        map2.setPlaceId(2);
        maps.add(map2);

        PlaceCategoryMap map3 = new PlaceCategoryMap();
        map3.setCategoryId(1);
        map3.setPlaceId(3);
        maps.add(map3);

        PlaceCategoryMap map4 = new PlaceCategoryMap();
        map4.setCategoryId(1);
        map4.setPlaceId(4);
        maps.add(map4);

        PlaceCategoryMap map5 = new PlaceCategoryMap();
        map5.setCategoryId(2);
        map5.setPlaceId(1);
        maps.add(map5);

        PlaceCategoryMap map6 = new PlaceCategoryMap();
        map6.setCategoryId(3);
        map6.setPlaceId(1);
        maps.add(map6);

        PlaceCategoryMap map7 = new PlaceCategoryMap();
        map7.setCategoryId(4);
        map7.setPlaceId(1);
        maps.add(map7);

        PlaceCategoryMap map8 = new PlaceCategoryMap();
        map8.setCategoryId(2);
        map8.setPlaceId(2);
        maps.add(map8);

        PlaceCategoryMap map9 = new PlaceCategoryMap();
        map9.setCategoryId(3);
        map9.setPlaceId(4);
        maps.add(map9);

        PlaceCategoryMap map10 = new PlaceCategoryMap();
        map10.setCategoryId(2);
        map10.setPlaceId(3);
        maps.add(map10);

        placeCategoryMapRepository.saveAll(maps);
    }

    @Test
    @Order(4)
    public void insertGatheringBoardTest() {
        //번개모임 정보 세팅
        List<GatheringBoard> boardList = new ArrayList<>();

        boardList.add(new GatheringBoard()
                .builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .gatherCnt(2)
                .totalCnt(5)
                .isExpired(false)
                .date(LocalDate.now())
                .place(placeRepository.findById(1).orElse(null))
                .title("설입에서 같이 빵먹으면서 대화하실 분!!")
                .text("안녕하세용 설입 리멤버미에서 같이 만나서 놀사람 구해요!!\n 이상한 사람은 아닙니다.")
                .userId(1)
                .build());

        boardList.add(new GatheringBoard()
                .builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .gatherCnt(3)
                .totalCnt(6)
                .isExpired(false)
                .date(LocalDate.now())
                .place(placeRepository.findById(1).orElse(null))
                .title("설입 리멤버미에서 같이 놀 사람 구해요")
                .text("여기 테라스 예쁘더라구용")
                .userId(2)
                .build());

        boardList.add(new GatheringBoard()
                .builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .gatherCnt(1)
                .totalCnt(5)
                .isExpired(false)
                .date(LocalDate.now())
                .place(placeRepository.findById(2).orElse(null))
                .title("컴즈커피 같이 갈사람구해요!")
                .text("컴즈커피라고 아시나요?")
                .userId(3)
                .build());

        boardList.add(new GatheringBoard()
                .builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .gatherCnt(1)
                .totalCnt(3)
                .isExpired(false)
                .date(LocalDate.now())
                .place(placeRepository.findById(4).orElse(null))
                .title("지금 홍대이신 분 계신가요?")
                .text("지금 홍대이신분중에서 같이 코딩할 사람 있나요?")
                .userId(1)
                .build());

        boardList.add(new GatheringBoard()
                .builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .gatherCnt(2)
                .totalCnt(3)
                .isExpired(false)
                .date(LocalDate.now())
                .place(placeRepository.findById(5).orElse(null))
                .title("홍대 모각코하실분!")
                .text("기왕이면 백엔드개발자분이었으면 좋겠어요! 같이 고민하면서 맛있는거 먹어요 ㅎㅎ 사드릴게요")
                .userId(3)
                .build());

        boardList.add(new GatheringBoard()
                .builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .gatherCnt(2)
                .totalCnt(5)
                .isExpired(false)
                .date(LocalDate.now())
                .place(placeRepository.findById(3).orElse(null))
                .title("커피덕 🐤")
                .text("내일 커피덕에서 같이 공부할 분 구해요 ! ")
                .userId(2)
                .build());

        boardList.add(new GatheringBoard()
                .builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .gatherCnt(2)
                .totalCnt(5)
                .isExpired(false)
                .date(LocalDate.now())
                .place(placeRepository.findById(1).orElse(null))
                .title("설입에서 같이 빵먹으면서 대화하실 분!!")
                .text("안녕하세용 설입 리멤버미에서 같이 만나서 놀사람 구해요!!\n 이상한 사람은 아닙니다.")
                .userId(1)
                .build());

        boardList.add(new GatheringBoard()
                .builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .gatherCnt(2)
                .totalCnt(5)
                .isExpired(false)
                .date(LocalDate.now())
                .place(placeRepository.findById(1).orElse(null))
                .title("홍대 코딩하실분!")
                .text("코테준비하시는 분 계신가요? 같이 공부해요!")
                .userId(6)
                .build());

        gatheringRepository.saveAll(boardList);
    }





    @Test
    @Order(5)
    public void insertUserTest() {
        List<String> userNameList = Arrays.asList("생갈치 1호의 행방불명", "오즈의 맙소사","반지하 제왕", "김대희","박다정", "순데렐라", "배숙희라빈스");

        for(int i = 0; i < userNameList.size(); i++) {
            KakaoUserDto user = new KakaoUserDto();
            user.setUsername("test" + i);
            user.setPassword("1234");
            user.setNickname(userNameList.get(i));
            user.setProfilePicture("/images/profile/%d.jpeg".formatted(i+1));

            userService.saveKakaoUser(user);
        }
    }


}
