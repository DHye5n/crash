package com.example.crash.model.registration;

import com.example.crash.model.crashsession.CrashSession;
import com.example.crash.model.entity.RegistrationEntity;
import com.example.crash.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Registration {

    private Long registrationId;
    private User user;
    private CrashSession session;

    public static Registration from(RegistrationEntity registrationEntity) {
        return new Registration(
                registrationEntity.getRegistrationId(),
                User.from(registrationEntity.getUser()),
                CrashSession.from(registrationEntity.getSession())
        );
    }
}
