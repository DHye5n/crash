package com.example.crash.model.crashsession;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class CrashSessionPostRequestBody {

    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    @NotNull
    private CrashSessionCategory category;
    @NotNull
    private ZonedDateTime dateTime;
    @NotNull
    private Long speakerId;

}
