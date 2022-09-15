package com.lion.youranmok.login.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Kakao_User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String nickname;

    @Column(length = 200, nullable = false)
    private String email;

    @Column(length = 500, nullable = true)
    private String profile_picture;

    @Builder
    public Kakao_User(String nickname, String email, String profile_picture){
        this.nickname=nickname;
        this.email=email;
        this.profile_picture=profile_picture;
    }
}

