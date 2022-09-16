package com.lion.youranmok.security.dto;

import com.lion.youranmok.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class MemberContext extends org.springframework.security.core.userdetails.User {

    private final int id;
    private final String profilePicture;

    public MemberContext(User user, List<GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.id = user.getId();
        this.profilePicture = user.getProfilePicture();
    }

}
