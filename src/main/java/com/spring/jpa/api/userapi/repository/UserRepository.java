package com.spring.jpa.api.userapi.repository;

import com.spring.jpa.api.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, String> {

//    //쿼리 메서드
//
//    // 이메일로 회원정보 조회
//    Optional<User> findByEmail(String email);
//
//    // 이메일 중복 체크
//    //@Query("SELECT COUNT(*) FROM User u WHERE u.email = ?1")
//    boolean existsByEmail(String email);

}