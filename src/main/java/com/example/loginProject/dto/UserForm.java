package com.example.loginProject.dto;

import com.example.loginProject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserForm {
    private Long id;
    private String name;
    private String email;
    private String password;

    public User toEntity() {
        return new User(id, name, email, password);
    }
}
