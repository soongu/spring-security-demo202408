// src/main/java/com/example/securitydemo/chap02_02/controller/AuthController.java
package com.example.securitydemo.chap02_02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/admin")
    public String admin() {
        return "Admin page";
    }

    @GetMapping("/user")
    public String user() {
        return "User page";
    }
}
