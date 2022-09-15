package com.lion.youranmok.login.dto;

import com.lion.youranmok.user.entity.User;

import java.time.LocalDateTime;

public class KakaoUserForm {
    private String nickname;
    private String profile_picture;
    private LocalDateTime created_at;

    public KakaoUserForm(String nickname, String email, String profile_picture, LocalDateTime created_at) {
        this.nickname = nickname;
        this.profile_picture = profile_picture;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "KakaoUserForm{" +
                "nickname='" + nickname + '\'' +
                ", profile_pictrue='" + profile_picture + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
    public User toEntity(){
        return new User(null, nickname, profile_picture,created_at);
    }
}
