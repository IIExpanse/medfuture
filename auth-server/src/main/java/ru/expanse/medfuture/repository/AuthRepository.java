package ru.expanse.medfuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.expanse.medfuture.model.User;

@Repository
public interface AuthRepository extends JpaRepository<User, String> {



    User findBy(String login);
}
