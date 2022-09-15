package com.lion.youranmok.user.entity;

import com.lion.youranmok.login.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String nickname;

    @Column(length = 200, nullable = false)
    private String username;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 500, nullable = false)
    private String profilePicture;

    @Builder
    public User(Integer id, String nickname, String profilePicture, LocalDateTime created_at){
        this.id=id;
        this.nickname=nickname;
        this.profilePicture = profilePicture;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Kakao_User{" +
                "created_at=" + created_at +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                ", profile_picture='" + profilePicture + '\'' +
                '}';
    }
}

