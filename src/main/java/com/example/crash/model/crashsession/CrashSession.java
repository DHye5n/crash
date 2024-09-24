package com.example.crash.model.crashsession;

import com.example.crash.model.entity.CrashSessionEntity;
import com.example.crash.model.sessionspeaker.SessionSpeaker;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class CrashSession {

    private Long sessionId;
    private String title;
    private String body;
    private CrashSessionCategory category;
    private ZonedDateTime dateTime;
    private SessionSpeaker speaker;

    public static CrashSession from(CrashSessionEntity crashSessionEntity) {
        return new CrashSession(
                crashSessionEntity.getSessionId(),
                crashSessionEntity.getTitle(),
                crashSessionEntity.getBody(),
                crashSessionEntity.getCategory(),
                crashSessionEntity.getDateTime(),
                SessionSpeaker.from(crashSessionEntity.getSpeaker())
        );
    }
}
