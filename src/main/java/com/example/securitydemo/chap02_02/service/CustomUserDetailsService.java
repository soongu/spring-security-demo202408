// src/main/java/com/example/securitydemo/chap02_02/service/CustomUserDetailsService.java
package com.example.securitydemo.chap02_02.service;

import com.example.securitydemo.chap02_02.entity.User;
import com.example.securitydemo.chap02_02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // `UserRepository` 인터페이스 주입을 통해 데이터베이스와의 연동을 처리합니다.
    // 이 필드는 final로 선언되어 있으며, Lombok의 @RequiredArgsConstructor로 자동 생성됩니다.
    private final UserRepository userRepository;

    /**
     * 주어진 사용자 이름(username)에 따라 사용자 세부 정보를 로드하는 메서드입니다.
     * 이 메서드는 스프링 시큐리티의 인증 과정에서 호출되며, 사용자 이름을 기반으로 사용자를 찾고,
     * 그에 대한 인증 정보를 반환합니다.
     *
     * @param username 사용자의 이름 (로그인 시 입력된 사용자 이름)
     * @return UserDetails 인터페이스를 구현한 사용자 정보 객체
     * @throws UsernameNotFoundException 사용자 이름으로 사용자를 찾지 못했을 경우 발생
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 이름으로 데이터베이스에서 사용자 정보를 조회합니다.
        User user = userRepository.findByUsername(username)
                // 사용자가 없을 경우, 예외를 발생시켜 인증 실패 처리합니다.
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // UserBuilder를 사용해 스프링 시큐리티에서 사용할 UserDetails 객체를 생성합니다.
        UserBuilder builder = withUsername(username);

        // 데이터베이스에서 조회된 사용자의 암호를 설정합니다.
        builder.password(user.getPassword());

        // 사용자의 역할(Role)을 설정합니다.
        builder.roles(user.getRole());

        // 생성된 UserDetails 객체를 반환합니다.
        return builder.build();
    }
}