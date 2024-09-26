package com.example.crash.model.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationPostRequestBody {

    @NotNull
    private Long sessionId;

}
