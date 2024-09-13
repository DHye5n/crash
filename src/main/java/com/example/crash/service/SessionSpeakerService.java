package com.example.crash.service;

import com.example.crash.exception.sessionspeaker.SessionSpeakerNotFoundException;
import com.example.crash.model.entity.SessionSpeakerEntity;
import com.example.crash.model.sessionspeaker.SessionSpeaker;
import com.example.crash.model.sessionspeaker.SessionSpeakerPatchRequestBody;
import com.example.crash.model.sessionspeaker.SessionSpeakerPostRequestBody;
import com.example.crash.repository.SessionSpeakerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionSpeakerService {

    private final SessionSpeakerEntityRepository sessionSpeakerEntityRepository;


    public List<SessionSpeaker> getSessionSpeakers() {
        List<SessionSpeakerEntity> sessionSpeakerEntities = sessionSpeakerEntityRepository.findAll();
        return sessionSpeakerEntities
                .stream()
                .map(SessionSpeaker::from)
                .collect(Collectors.toList());
    }

    public SessionSpeaker getSessionSpeaker(Long speakerId) {
        SessionSpeakerEntity sessionSpeakerEntity = getSessionSpeakerEntityBySpeakerId(speakerId);
        return SessionSpeaker.from(sessionSpeakerEntity);
    }

    public SessionSpeakerEntity getSessionSpeakerEntityBySpeakerId(Long speakerId) {
        return sessionSpeakerEntityRepository.findById(speakerId)
                .orElseThrow(() -> new SessionSpeakerNotFoundException(speakerId));
    }

    public SessionSpeaker createSessionSpeaker(SessionSpeakerPostRequestBody sessionSpeakerPostRequestBody) {
        SessionSpeakerEntity sessionSpeakerEntity = SessionSpeakerEntity.of(
                sessionSpeakerPostRequestBody.getCompany(),
                sessionSpeakerPostRequestBody.getName(),
                sessionSpeakerPostRequestBody.getDescription()
        );

        return SessionSpeaker.from(sessionSpeakerEntityRepository.save(sessionSpeakerEntity));
    }

    public SessionSpeaker updateSessionSpeaker(
            Long speakerId,
            SessionSpeakerPatchRequestBody sessionSpeakerPatchRequestBody) {
        SessionSpeakerEntity sessionSpeakerEntity = getSessionSpeakerEntityBySpeakerId(speakerId);

        if (!ObjectUtils.isEmpty(sessionSpeakerPatchRequestBody.getCompany())) {
            sessionSpeakerEntity.setCompany(sessionSpeakerPatchRequestBody.getCompany());
        }

        if (!ObjectUtils.isEmpty(sessionSpeakerPatchRequestBody.getName())) {
            sessionSpeakerEntity.setCompany(sessionSpeakerPatchRequestBody.getName());
        }

        if (!ObjectUtils.isEmpty(sessionSpeakerPatchRequestBody.getDescription())) {
            sessionSpeakerEntity.setCompany(sessionSpeakerPatchRequestBody.getDescription());
        }

        return SessionSpeaker.from(sessionSpeakerEntityRepository.save(sessionSpeakerEntity));
    }

    public void deleteSessionSpeaker(Long speakerId) {
        SessionSpeakerEntity sessionSpeakerEntity = getSessionSpeakerEntityBySpeakerId(speakerId);
        sessionSpeakerEntityRepository.delete(sessionSpeakerEntity);
    }
}
