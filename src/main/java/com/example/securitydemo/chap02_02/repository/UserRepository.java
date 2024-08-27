// src/main/java/com/example/securitydemo/chap02_02/repository/UserRepository.java
package com.example.securitydemo.chap02_02.repository;

import com.example.securitydemo.chap02_02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
