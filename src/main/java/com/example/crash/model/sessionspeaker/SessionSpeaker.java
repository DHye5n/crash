package com.example.crash.model.sessionspeaker;

import com.example.crash.model.entity.SessionSpeakerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionSpeaker {

    private Long speakerId;
    private String company;
    private String name;
    private String description;
    private String profile;

    public static SessionSpeaker from(SessionSpeakerEntity sessionSpeakerEntity) {
        return new SessionSpeaker(
                sessionSpeakerEntity.getSpeakerId(),
                sessionSpeakerEntity.getCompany(),
                sessionSpeakerEntity.getName(),
                sessionSpeakerEntity.getDescription(),
                sessionSpeakerEntity.getProfile()
        );
    }
}
