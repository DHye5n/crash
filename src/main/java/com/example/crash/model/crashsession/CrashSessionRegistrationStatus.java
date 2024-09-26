package com.example.crash.model.crashsession;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrashSessionRegistrationStatus {

    private Long sessionId;
    private boolean isRegistered;
    private Long registrationId;
}
