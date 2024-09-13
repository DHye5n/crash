package com.example.crash.model.sessionspeaker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionSpeakerPatchRequestBody {


    private String company;

    private String name;

    private String description;
}
