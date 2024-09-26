package com.example.crash.service;

import com.example.crash.exception.registration.RegistrationAlreadyExistsException;
import com.example.crash.exception.registration.RegistrationNotFoundException;
import com.example.crash.model.crashsession.CrashSessionRegistrationStatus;
import com.example.crash.model.entity.CrashSessionEntity;
import com.example.crash.model.entity.RegistrationEntity;
import com.example.crash.model.entity.UserEntity;
import com.example.crash.model.registration.Registration;
import com.example.crash.model.registration.RegistrationPostRequestBody;
import com.example.crash.repository.RegistrationEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    
    private final RegistrationEntityRepository registrationEntityRepository;
    private final CrashSessionService crashSessionService;


    public List<Registration> getRegistrations(UserEntity currentUser) {
        List<RegistrationEntity> registrationEntities = registrationEntityRepository.findByUser(currentUser);

        return registrationEntities.stream().map(Registration::from).collect(Collectors.toList());
    }

    public Registration getRegistration(Long registrationId, UserEntity currentUser) {
        RegistrationEntity registrationEntity =
                getRegistrationEntityByRegistrationIdAndUserEntity(registrationId, currentUser);

        return Registration.from(registrationEntity);
    }

    public Registration createRegistration(RegistrationPostRequestBody registrationPostRequestBody, UserEntity currentUser) {

        CrashSessionEntity crashSessionEntity =
                crashSessionService.getCrashSessionEntityBySessionId(registrationPostRequestBody.getSessionId());

        registrationEntityRepository.findByUserAndSession(currentUser, crashSessionEntity)
                .ifPresent(registrationEntity -> {
                    throw new RegistrationAlreadyExistsException(registrationEntity.getRegistrationId(), currentUser);
                });

        RegistrationEntity registrationEntity = RegistrationEntity.of(currentUser, crashSessionEntity);

        return Registration.from(registrationEntityRepository.save(registrationEntity));
    }

    public void deleteRegistration(Long registrationId, UserEntity currentUser) {
        RegistrationEntity registrationEntity =
                getRegistrationEntityByRegistrationIdAndUserEntity(registrationId, currentUser);
        registrationEntityRepository.delete(registrationEntity);
    }

    public RegistrationEntity getRegistrationEntityByRegistrationIdAndUserEntity(Long registrationId, UserEntity currentUser) {

        return registrationEntityRepository.findByRegistrationIdAndUser(registrationId, currentUser)
                .orElseThrow(() -> new RegistrationNotFoundException(registrationId, currentUser));
    }

    public CrashSessionRegistrationStatus getCrashSessionRegistrationStatusBySessionIdAndCurrentUser(Long sessionId, UserEntity currentUser) {
        CrashSessionEntity crashSessionEntity = crashSessionService.getCrashSessionEntityBySessionId(sessionId);
        Optional<RegistrationEntity> registrationEntity =
                registrationEntityRepository.findByUserAndSession(currentUser, crashSessionEntity);

        return new CrashSessionRegistrationStatus(
                sessionId,
                registrationEntity.isPresent(),
                registrationEntity.map(RegistrationEntity::getRegistrationId).orElse(null));
    }
}
