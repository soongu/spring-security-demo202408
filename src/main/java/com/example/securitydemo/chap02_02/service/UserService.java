
// src/main/java/com/example/securitydemo/chap02_02/service/UserService.java
package com.example.securitydemo.chap02_02.service;

import com.example.securitydemo.chap02_02.entity.User;
import com.example.securitydemo.chap02_02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // 비밀번호를 암호화하여 저장
        user.setRole(role);
        return userRepository.save(user);
    }
}