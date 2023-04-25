drop index if exists ix_auth_username;
drop table if exists authorities;
drop table if exists users;

create table IF NOT EXISTS users(
                      username VARCHAR(50) NOT NULL PRIMARY KEY ,
                      password VARCHAR(500) NOT NULL,
                      enabled BOOLEAN NOT NULL,
                      CONSTRAINT username_in_lowercase CHECK (username LIKE LOWER(username))
);

create table IF NOT EXISTS authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             CONSTRAINT fk_authorities_users foreign key(username) references users(username)
);
create unique index IF NOT EXISTS ix_auth_username on authorities (username,authority);