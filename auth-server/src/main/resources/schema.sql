CREATE TABLE IF NOT EXISTS users
(
    login    VARCHAR(128) PRIMARY KEY,
    password VARCHAR(128),
    CONSTRAINT login_length CHECK (LENGTH(login) <= 25),
    CONSTRAINT password_length CHECK (LENGTH(password) <= 14)
);