// src/main/java/com/example/securitydemo/chap02_02/entity/User.java
package com.example.securitydemo.chap02_02.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "tbl_security_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

}