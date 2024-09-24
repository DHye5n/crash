package com.example.crash.model.crashsession;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class CrashSessionPatchRequestBody {


    private String title;

    private String body;

    private CrashSessionCategory category;

    private ZonedDateTime dateTime;

    private Long speakerId;

}
