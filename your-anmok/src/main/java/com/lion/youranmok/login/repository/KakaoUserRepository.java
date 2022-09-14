package com.lion.youranmok.login.repository;

import com.lion.youranmok.login.entity.Kakao_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface KakaoUserRepository extends JpaRepository<Kakao_User, Integer> {
    Kakao_User findByEmail(String email);

    Optional<Kakao_User> findByUsername(String username);

}
