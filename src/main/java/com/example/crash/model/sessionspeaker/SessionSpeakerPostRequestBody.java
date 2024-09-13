package com.example.crash.model.sessionspeaker;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class SessionSpeakerPostRequestBody {

    @NotEmpty
    private String company;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
}
