package ru.expanse.medfuture.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.expanse.medfuture.repository.AuthRepository;
import ru.expanse.medfuture.service.AuthService;

@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final AuthRepository repository;



    @Override
    public boolean authenticate(String login, String password) {
        boolean result = encoder.matches(password, repository.findBy(login).getPassword());

        log.trace(String.format(
                "Результат проверки пароля для пользователя с логином %s: %b",
                login, result));
        return result;
    }
}
