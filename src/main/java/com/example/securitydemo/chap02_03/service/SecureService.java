// src/main/java/com/example/securitydemo/chap02_03/service/SecureService.java
package com.example.securitydemo.chap02_03.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecureService {

    @Secured("ROLE_USER")
    public String userAccess() {
        return "User Content";
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Content";
    }
}
