package com.lion.youranmok.security.dto;

import com.lion.youranmok.login.entity.Kakao_User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class MemberContext extends User {

    private final int id;
    private final String profilePicture;

    public MemberContext(Kakao_User user, List<GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.id = user.getId();
        this.profilePicture = user.getProfile_picture();
    }

}
