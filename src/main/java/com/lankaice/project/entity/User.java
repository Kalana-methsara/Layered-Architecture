package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
}
