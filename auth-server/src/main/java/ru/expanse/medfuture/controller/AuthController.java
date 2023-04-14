package ru.expanse.medfuture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.expanse.medfuture.service.AuthService;

@RestController
@RequestMapping(path = "/login")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @GetMapping
    public ResponseEntity<Boolean> authenticate(
            final String login,
            final String password) {
        return ResponseEntity.ok(service.authenticate(login, password));
    }
}
