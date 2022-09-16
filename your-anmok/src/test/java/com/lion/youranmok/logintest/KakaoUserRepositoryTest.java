package com.lion.youranmok.logintest;

import com.lion.youranmok.login.entity.Kakao_User;
import com.lion.youranmok.login.repository.KakaoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class KakaoUserRepositoryTest {
    @Autowired
    KakaoUserRepository kakaoUserRepository;

    @Test
    public void Datetime_Test() {
        //given
        LocalDateTime created_at = LocalDateTime.of(2020,03,17,0,0,0);
        kakaoUserRepository.save(Kakao_User.builder()
                .nickname("nickname")
                .email("email")
                .profile_picture("profile_picture")
                .build());

        //when
        List<Kakao_User> kakaoUserList = kakaoUserRepository.findAll();

        //then

        Kakao_User kakaoUser = kakaoUserList.get(0);

        System.out.println(">>>>>>>>>>> createDate="+ kakaoUser.getCreated_at()
                );
        assertThat(kakaoUser.getCreated_at()).isAfter(created_at);

    }
    /*
    @Test
    void testJpa(){
        Kakao_User q = this.kakaoUserRepository.findByEmail("psh4619@naver.com");
        assertEquals(2, q.getId());
    }*/

}