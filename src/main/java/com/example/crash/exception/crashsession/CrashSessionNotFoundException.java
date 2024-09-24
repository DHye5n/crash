package com.example.crash.exception.crashsession;

import com.example.crash.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class CrashSessionNotFoundException extends ClientErrorException {

    public CrashSessionNotFoundException() {
        super(HttpStatus.NOT_FOUND, "CrashSession not found");
    }

    public CrashSessionNotFoundException(Long sessionId) {
        super(HttpStatus.NOT_FOUND, "CrashSession with sessionId " + sessionId + " not found.");
    }
}
