package com.lion.youranmok.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUserDto {

    private int id;
    private String nickname;
    private String username;
    private String password;
    private String profilePicture;
    private LocalDateTime created_at;

}
