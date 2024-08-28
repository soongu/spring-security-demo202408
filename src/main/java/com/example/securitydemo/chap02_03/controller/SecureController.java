// src/main/java/com/example/securitydemo/chap02_03/controller/SecureController.java
package com.example.securitydemo.chap02_03.controller;

import com.example.securitydemo.chap02_03.service.SecureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
@RequiredArgsConstructor
public class SecureController {

    private final SecureService secureService;

    @GetMapping("/user")
    public String getUserContent() {
        return secureService.userAccess();
    }

    @GetMapping("/admin")
    public String getAdminContent() {
        return secureService.adminAccess();
    }
}
