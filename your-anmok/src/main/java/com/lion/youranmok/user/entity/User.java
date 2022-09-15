package com.lion.youranmok.user.entity;

import com.lion.youranmok.login.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    private int id;

    @Column(length = 30)
    private String nickname;

    @Column(length = 200)
    private String profilePicture;

}
