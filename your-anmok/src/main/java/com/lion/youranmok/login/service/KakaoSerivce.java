package com.lion.youranmok.login.service;

import com.lion.youranmok.login.dto.KakaoUserDto;
import com.lion.youranmok.login.entity.Kakao_User;
import com.lion.youranmok.login.repository.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KakaoSerivce {

    private final KakaoUserRepository kakaoUserRepository;

    public Kakao_User findByEmail(String data) {
        return kakaoUserRepository.findByEmail(data);
    }

    public void save(KakaoUserDto kakaoUserDto) {

        Kakao_User user = dtoToEntity(kakaoUserDto);

        kakaoUserRepository.save(user);

    }

    private Kakao_User dtoToEntity(KakaoUserDto kakaoUserDto) {

        Kakao_User user = Kakao_User.builder()
                .nickname(kakaoUserDto.getNickname())
                .username(kakaoUserDto.getUsername())
                .password(kakaoUserDto.getPassword())
                .profile_picture(kakaoUserDto.getProfilePicture())
                .email(kakaoUserDto.getEmail())
                .build();

        return user;

    }
}
