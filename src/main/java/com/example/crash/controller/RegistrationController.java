package com.example.crash.controller;

import com.example.crash.model.entity.UserEntity;
import com.example.crash.model.registration.Registration;
import com.example.crash.model.registration.RegistrationPostRequestBody;
import com.example.crash.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<List<Registration>> getRegistrations(Authentication authentication) {
        List<Registration> registrations =
                registrationService.getRegistrations((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/{registrationId}")
    public ResponseEntity<Registration> getRegistration(@PathVariable Long registrationId, Authentication authentication) {
        Registration registration =
                registrationService.getRegistration(registrationId, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(registration);
    }

    @PostMapping
    public ResponseEntity<Registration> createRegistration(
            @Valid @RequestBody RegistrationPostRequestBody registrationPostRequestBody, Authentication authentication) {

        Registration registration =
                registrationService.createRegistration(registrationPostRequestBody, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(registration);
    }

    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long registrationId, Authentication authentication) {
        registrationService.deleteRegistration(registrationId, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.noContent().build();
    }
}
