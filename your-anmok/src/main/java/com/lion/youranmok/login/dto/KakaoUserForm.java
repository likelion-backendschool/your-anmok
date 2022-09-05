package com.lion.youranmok.login.dto;

import com.lion.youranmok.login.entity.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

public class KakaoUserForm {
    private String nickname;
    private String email;
    private String profile_pictrue;
    private LocalDateTime created_at;

    public KakaoUserForm(String nickname, String email, String profile_pictrue, LocalDateTime created_at) {
        this.nickname = nickname;
        this.email = email;
        this.profile_pictrue = profile_pictrue;
        this.created_at = created_at;
    }
}
