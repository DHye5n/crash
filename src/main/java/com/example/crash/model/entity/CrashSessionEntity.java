package com.example.crash.model.entity;

import com.example.crash.model.crashsession.CrashSessionCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;


@Entity
@Table(name = "crashsession")
@Getter
@Setter
public class CrashSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CrashSessionCategory category;

    @Column(nullable = false)
    private ZonedDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "speakerid")
    private SessionSpeakerEntity speaker;

    public static CrashSessionEntity of(String title, String body,
                                        CrashSessionCategory crashSessionCategory, ZonedDateTime dateTime,
                                        SessionSpeakerEntity sessionSpeakerEntity) {
        CrashSessionEntity crashSessionEntity = new CrashSessionEntity();
        crashSessionEntity.setTitle(title);
        crashSessionEntity.setBody(body);
        crashSessionEntity.setCategory(crashSessionCategory);
        crashSessionEntity.setDateTime(dateTime);
        crashSessionEntity.setSpeaker(sessionSpeakerEntity);


        return crashSessionEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrashSessionEntity that = (CrashSessionEntity) o;
        return Objects.equals(sessionId, that.sessionId)
                && Objects.equals(title, that.title)
                && Objects.equals(body, that.body)
                && Objects.equals(category, that.category)
                && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, title, body, category, dateTime);
    }
}
