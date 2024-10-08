package com.example.crash.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "registration",
        indexes = {@Index(
                name = "registration_userid_sessionid_idx",
                columnList = "userid, sessionid",
                unique = true
        )}
)
@Getter
@Setter
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "sessionid")
    private CrashSessionEntity session;

    @Column
    private ZonedDateTime createdDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationEntity that = (RegistrationEntity) o;
        return Objects.equals(registrationId, that.registrationId)
                && Objects.equals(user, that.user)
                && Objects.equals(session, that.session)
                && Objects.equals(createdDateTime, that.createdDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationId, user, session, createdDateTime);
    }

    public static RegistrationEntity of(UserEntity userEntity, CrashSessionEntity sessionEntity) {
        RegistrationEntity registrationEntity = new RegistrationEntity();
        registrationEntity.setUser(userEntity);
        registrationEntity.setSession(sessionEntity);
        return registrationEntity;
    }

    @PrePersist
    private void prePersist() {
        this.createdDateTime = ZonedDateTime.now();
    }
}
