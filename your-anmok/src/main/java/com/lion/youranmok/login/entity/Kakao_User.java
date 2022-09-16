package com.lion.youranmok.login.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Kakao_User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String nickname;

    @Column(length = 200, nullable = false)
    private String email;

    @Column(length = 500, nullable = false)
    private String profile_picture;

    @Builder
    public Kakao_User(Integer id, String nickname, String email, String profile_picture, LocalDateTime created_at){
        this.id=id;
        this.nickname=nickname;
        this.email=email;
        this.profile_picture=profile_picture;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Kakao_User{" +
                "created_at=" + created_at +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", profile_picture='" + profile_picture + '\'' +
                '}';
    }
}

