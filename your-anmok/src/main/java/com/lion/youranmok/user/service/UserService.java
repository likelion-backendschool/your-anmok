package com.lion.youranmok.user.service;

import com.lion.youranmok.login.dto.KakaoUserDto;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveKakaoUser(KakaoUserDto dto) {

        User user = kakaoUserDtoToEntity(dto);
        userRepository.save(user);

    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username).orElse(null);

    }

    public void changeNickname(KakaoUserDto dto) {

        Optional<User> result = userRepository.findById(dto.getId());

        if (result.isPresent()) {
            User user = result.get();
            user.setNickname(dto.getNickname());
            userRepository.save(user);
        }

    }

    private User kakaoUserDtoToEntity(KakaoUserDto dto) {

        User user = User.builder()
                .id(dto.getId())
                .nickname(dto.getNickname())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .profilePicture(dto.getProfilePicture())
                .build();

        return user;

    }



}
