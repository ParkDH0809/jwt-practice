package com.example.jwt_practice.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String name;

    private String tel;

    private String role;
}
