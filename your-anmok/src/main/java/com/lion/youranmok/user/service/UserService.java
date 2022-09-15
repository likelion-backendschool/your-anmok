package com.lion.youranmok.user.service;

import com.lion.youranmok.login.dto.KakaoUserDto;
import com.lion.youranmok.login.entity.Kakao_User;
import com.lion.youranmok.login.repository.KakaoUserRepository;
import com.lion.youranmok.user.dto.UserDto;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KakaoUserRepository kakaoUserRepository;

    public void saveKakaoUser(KakaoUserDto dto) {

        int kakaoUserId = kakaoUserRepository.findByUsername(dto.getUsername()).get().getId();

        UserDto userDto = UserDto.builder()
                .id(kakaoUserId)
                .nickname(dto.getNickname())
                .profilePicture(dto.getProfilePicture())
                .build();

        User user = dtoToEntity(userDto);
        userRepository.save(user);

    }

    private User dtoToEntity(UserDto userDto) {

        User user = User.builder()
                .id(userDto.getId())
                .nickname(userDto.getNickname())
                .profilePicture(userDto.getProfilePicture())
                .build();

        return user;

    }

}
