package ru.expanse.medfuture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    boolean authenticate(String login, String password);
}
