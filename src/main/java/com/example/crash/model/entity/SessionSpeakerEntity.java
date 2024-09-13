package com.example.crash.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Random;


@Entity
@Table(name = "sessionspeaker")
@Getter
@Setter
public class SessionSpeakerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speakerId;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String profile;


    public static SessionSpeakerEntity of(String company, String name, String description) {
        SessionSpeakerEntity sessionSpeakerEntity = new SessionSpeakerEntity();
        sessionSpeakerEntity.setCompany(company);
        sessionSpeakerEntity.setName(name);
        sessionSpeakerEntity.setDescription(description);
        sessionSpeakerEntity.setProfile(
                "https://dev-jayce.github.io/public/profile" + (new Random().nextInt(100) + 1) + ".png"
        );


        return sessionSpeakerEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionSpeakerEntity that = (SessionSpeakerEntity) o;
        return Objects.equals(speakerId, that.speakerId) && Objects.equals(company, that.company) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(profile, that.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speakerId, company, name, description, profile);
    }
}
