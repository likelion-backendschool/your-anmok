package com.lion.youranmok.user.repository;

import com.lion.youranmok.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByNickname(String mentionTo);

    Optional<User> findByUsername(String username);
}
