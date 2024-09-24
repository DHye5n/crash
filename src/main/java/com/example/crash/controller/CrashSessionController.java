package com.example.crash.controller;


import com.example.crash.model.crashsession.CrashSession;
import com.example.crash.model.crashsession.CrashSessionPatchRequestBody;
import com.example.crash.model.crashsession.CrashSessionPostRequestBody;
import com.example.crash.service.CrashSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/crash-sessions")
@RequiredArgsConstructor
public class CrashSessionController {

    private final CrashSessionService crashSessionService;

    @GetMapping
    public ResponseEntity<List<CrashSession>> getCrashSessions() {
        List<CrashSession> crashSessions = crashSessionService.getCrashSessions();
        return ResponseEntity.ok(crashSessions);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<CrashSession> getCrashSession(@PathVariable Long sessionId) {
        CrashSession crashSession = crashSessionService.getCrashSession(sessionId);
        return ResponseEntity.ok(crashSession);
    }

    @PostMapping
    public ResponseEntity<CrashSession> createCrashSession(
            @Valid @RequestBody CrashSessionPostRequestBody crashSessionPostRequestBody) {
        CrashSession crashSession = crashSessionService.createCrashSession(crashSessionPostRequestBody);
        return ResponseEntity.ok(crashSession);
    }

    @PatchMapping("/{sessionId}")
    public ResponseEntity<CrashSession> updateCrashSession(
            @PathVariable Long sessionId,
            @RequestBody CrashSessionPatchRequestBody crashSessionPatchRequestBody) {
        CrashSession crashSession = crashSessionService.updateCrashSession(sessionId, crashSessionPatchRequestBody);
        return ResponseEntity.ok(crashSession);
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deleteCrashSession(@PathVariable Long sessionId) {
        crashSessionService.deleteCrashSession(sessionId);
        return ResponseEntity.noContent().build();
    }

}
