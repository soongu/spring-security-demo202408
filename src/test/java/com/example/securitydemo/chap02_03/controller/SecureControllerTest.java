// src/test/java/com/example/securitydemo/chap02_03/controller/SecureControllerTest.java
package com.example.securitydemo.chap02_03.controller;

import com.example.securitydemo.chap02_02.config.SecurityConfig;
import com.example.securitydemo.chap02_03.service.SecureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecureService secureService;

    @Test
    @WithMockUser(roles = "USER")
    void userAccess_withUserRole_shouldReturnUserContent() throws Exception {
        mockMvc.perform(get("/api/secure/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("User Content"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminAccess_withAdminRole_shouldReturnAdminContent() throws Exception {
        mockMvc.perform(get("/api/secure/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Admin Content"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void adminAccess_withUserRole_shouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/secure/admin"))
                .andExpect(status().isForbidden());
    }
}
