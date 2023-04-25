package ru.expanse.medfuture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<String> accessDenied() {
        return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/success")
    public ResponseEntity<String> accessGranted() {
        return ResponseEntity.ok("");
    }
}
