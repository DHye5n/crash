package com.example.crash.controller;


import com.example.crash.model.sessionspeaker.SessionSpeaker;
import com.example.crash.model.sessionspeaker.SessionSpeakerPatchRequestBody;
import com.example.crash.model.sessionspeaker.SessionSpeakerPostRequestBody;
import com.example.crash.service.SessionSpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/session-speakers")
@RequiredArgsConstructor
public class SessionSpeakerController {

    private final SessionSpeakerService sessionSpeakerService;

    @GetMapping
    public ResponseEntity<List<SessionSpeaker>> getSessionSpeakers() {
        List<SessionSpeaker> sessionSpeakers = sessionSpeakerService.getSessionSpeakers();
        return ResponseEntity.ok(sessionSpeakers);
    }

    @GetMapping("/{speakerId}")
    public ResponseEntity<SessionSpeaker> getSessionSpeaker(@PathVariable Long speakerId) {
        SessionSpeaker sessionSpeaker = sessionSpeakerService.getSessionSpeaker(speakerId);
        return ResponseEntity.ok(sessionSpeaker);
    }

    @PostMapping
    public ResponseEntity<SessionSpeaker> createSessionSpeaker(
            @Valid @RequestBody SessionSpeakerPostRequestBody sessionSpeakerPostRequestBody) {
        SessionSpeaker sessionSpeaker = sessionSpeakerService.createSessionSpeaker(sessionSpeakerPostRequestBody);
        return ResponseEntity.ok(sessionSpeaker);
    }

    @PatchMapping("/{speakerId}")
    public ResponseEntity<SessionSpeaker> updateSessionSpeaker(
            @PathVariable Long speakerId,
            @RequestBody SessionSpeakerPatchRequestBody sessionSpeakerPatchRequestBody) {
        SessionSpeaker sessionSpeaker = sessionSpeakerService.updateSessionSpeaker(speakerId, sessionSpeakerPatchRequestBody);
        return ResponseEntity.ok(sessionSpeaker);
    }

    @DeleteMapping("/{speakerId}")
    public ResponseEntity<Void> deleteSessionSpeaker(@PathVariable Long speakerId) {
        sessionSpeakerService.deleteSessionSpeaker(speakerId);
        return ResponseEntity.noContent().build();
    }

}
