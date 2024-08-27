// src/main/java/com/example/securitydemo/chap02_02/controller/UserController.java
package com.example.securitydemo.chap02_02.controller;

import com.example.securitydemo.chap02_02.entity.User;
import com.example.securitydemo.chap02_02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username,
                                               @RequestParam String password,
                                               @RequestParam String role) {
        User user = userService.registerUser(username, password, role);
        return ResponseEntity.ok("User registered with username: " + user.getUsername());
    }
}