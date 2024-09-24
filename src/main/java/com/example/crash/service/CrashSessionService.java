package com.example.crash.service;

import com.example.crash.exception.crashsession.CrashSessionNotFoundException;
import com.example.crash.model.crashsession.CrashSession;
import com.example.crash.model.crashsession.CrashSessionPatchRequestBody;
import com.example.crash.model.crashsession.CrashSessionPostRequestBody;
import com.example.crash.model.entity.CrashSessionEntity;
import com.example.crash.model.entity.SessionSpeakerEntity;
import com.example.crash.repository.CrashSessionEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrashSessionService {

    private final CrashSessionEntityRepository crashSessionEntityRepository;
    private final SessionSpeakerService sessionSpeakerService;

    public List<CrashSession> getCrashSessions() {
        return crashSessionEntityRepository.findAll().stream().map(CrashSession::from).collect(Collectors.toList());
    }

    public CrashSession getCrashSession(Long sessionId) {
        CrashSessionEntity crashSessionEntity = getCrashSessionEntityBySessionId(sessionId);
        return CrashSession.from(crashSessionEntity);
    }

    public CrashSession createCrashSession(CrashSessionPostRequestBody crashSessionPostRequestBody) {
        SessionSpeakerEntity sessionSpeakerEntity =
        sessionSpeakerService.getSessionSpeakerEntityBySpeakerId(crashSessionPostRequestBody.getSpeakerId());

        CrashSessionEntity crashSessionEntity =
        CrashSessionEntity.of(
                crashSessionPostRequestBody.getTitle(),
                crashSessionPostRequestBody.getBody(),
                crashSessionPostRequestBody.getCategory(),
                crashSessionPostRequestBody.getDateTime(),
                sessionSpeakerEntity);

        return CrashSession.from(crashSessionEntityRepository.save(crashSessionEntity));
    }

    public CrashSession updateCrashSession(Long sessionId, CrashSessionPatchRequestBody crashSessionPatchRequestBody) {

        CrashSessionEntity crashSessionEntity = getCrashSessionEntityBySessionId(sessionId);

        if (!ObjectUtils.isEmpty(crashSessionPatchRequestBody.getTitle())) {
            crashSessionEntity.setTitle(crashSessionPatchRequestBody.getTitle());
        }

        if (!ObjectUtils.isEmpty(crashSessionPatchRequestBody.getBody())) {
            crashSessionEntity.setBody(crashSessionPatchRequestBody.getBody());
        }

        if (!ObjectUtils.isEmpty(crashSessionPatchRequestBody.getCategory())) {
            crashSessionEntity.setCategory(crashSessionPatchRequestBody.getCategory());
        }

        if (!ObjectUtils.isEmpty(crashSessionPatchRequestBody.getDateTime())) {
            crashSessionEntity.setDateTime(crashSessionPatchRequestBody.getDateTime());
        }

        if (!ObjectUtils.isEmpty(crashSessionPatchRequestBody.getSpeakerId())) {
            SessionSpeakerEntity sessionSpeakerEntity =
                    sessionSpeakerService.getSessionSpeakerEntityBySpeakerId(crashSessionPatchRequestBody.getSpeakerId());
            crashSessionEntity.setSpeaker(sessionSpeakerEntity);
        }

        return CrashSession.from(crashSessionEntityRepository.save(crashSessionEntity));
    }

    public void deleteCrashSession(Long sessionId) {
        CrashSessionEntity crashSessionEntity = getCrashSessionEntityBySessionId(sessionId);
        crashSessionEntityRepository.delete(crashSessionEntity);
    }

    public CrashSessionEntity getCrashSessionEntityBySessionId(Long sessionId) {
        return crashSessionEntityRepository.findById(sessionId)
                .orElseThrow(() -> new CrashSessionNotFoundException(sessionId));
    }
}
