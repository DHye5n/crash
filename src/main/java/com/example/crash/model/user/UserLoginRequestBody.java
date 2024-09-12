package com.example.crash.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class UserLoginRequestBody {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

}
