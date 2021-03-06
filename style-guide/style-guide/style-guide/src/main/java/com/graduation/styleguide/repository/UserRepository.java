package com.graduation.styleguide.repository;

import com.graduation.styleguide.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByUserID(String userID);
}
