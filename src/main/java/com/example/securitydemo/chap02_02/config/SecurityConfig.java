// src/main/java/com/example/securitydemo/chap02_02/config/SecurityConfig.java
package com.example.securitydemo.chap02_02.config;

import com.example.securitydemo.chap02_02.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  // 이 클래스가 스프링의 설정 클래스임을 나타냅니다.
@RequiredArgsConstructor  // final 필드에 대해 생성자를 자동으로 생성해주는 Lombok 어노테이션입니다.
public class SecurityConfig {

    // 사용자 정보를 로드하는 서비스입니다. 스프링 컨텍스트에 의해 주입됩니다.
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * PasswordEncoder 빈을 생성합니다.
     * 이 경우 BCryptPasswordEncoder를 사용하여 비밀번호를 해시화합니다.
     * @return PasswordEncoder 인스턴스
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * DaoAuthenticationProvider 빈을 생성합니다.
     * DaoAuthenticationProvider는 스프링 시큐리티에서 사용자 인증을 처리하는데 사용됩니다.
     * customUserDetailsService를 통해 사용자 정보를 로드하고,
     * passwordEncoder를 통해 비밀번호를 검증합니다.
     * @return DaoAuthenticationProvider 인스턴스
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);  // 사용자 정보를 로드하는 서비스를 설정합니다.
        authProvider.setPasswordEncoder(passwordEncoder());  // 비밀번호 인코더를 설정합니다.
        return authProvider;
    }

    /**
     * SecurityFilterChain 빈을 생성하여 HTTP 보안 구성을 정의합니다.
     * 이 메서드는 애플리케이션의 보안 설정을 담당하며,
     * 스프링 시큐리티가 요청을 처리할 때 이 설정을 따릅니다.
     *
     * @param http HttpSecurity 인스턴스
     * @return SecurityFilterChain 인스턴스
     * @throws Exception 보안 설정 중 발생할 수 있는 예외
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/users/register")) // 특정 URL에 대해 CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // "/admin/**" 경로는 "ADMIN" 권한이 있는 사용자만 접근 가능하게 합니다.
                        .anyRequest().authenticated()  // 나머지 모든 요청은 인증된 사용자만 접근할 수 있습니다.
                )
                .formLogin(withDefaults());  // 기본 로그인 폼 설정을 사용합니다.

        return http.build();  // 보안 설정을 적용한 SecurityFilterChain을 반환합니다.
    }
}