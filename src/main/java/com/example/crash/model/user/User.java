package com.example.crash.model.user;

import com.example.crash.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class User {
    private Long userId;
    private String username;
    private String name;
    private String email;



    public static User from(UserEntity userEntity) {
        return new User(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }
}
