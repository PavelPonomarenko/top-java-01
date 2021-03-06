-- DROP TABLE IF EXISTS user_roles;
-- DROP TABLE IF EXISTS meals;
-- DROP TABLE IF EXISTS users;
-- DROP SEQUENCE IF EXISTS global_seq;
--
-- CREATE SEQUENCE global_seq START 100000;
--
-- CREATE TABLE users
-- (
--     id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
--     name       VARCHAR,
--     email      VARCHAR NOT NULL,
--     password   VARCHAR NOT NULL,
--     registered TIMESTAMP           DEFAULT now(),
--     enabled    BOOL                DEFAULT TRUE
-- );
-- CREATE UNIQUE INDEX unique_email ON USERS (email);
--
-- CREATE TABLE user_roles
-- (
--     user_id INTEGER NOT NULL,
--     role    VARCHAR,
--     CONSTRAINT user_roles_idx UNIQUE (user_id, role),
--     FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
-- );


DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS MEALS;
DROP TABLE IF EXISTS USERS;
DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ START 100000;

CREATE TABLE USERS
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    name       VARCHAR,
    email      VARCHAR   NOT NULL,
    password   VARCHAR   NOT NULL,
    registered TIMESTAMP NOT NULL  DEFAULT now(),
    enabled    BOOL                DEFAULT TRUE
);
CREATE UNIQUE INDEX unique_email ON USERS (email);

CREATE TABLE USER_ROLES
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE MEALS
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('GLOBAL_SEQ'),
    user_id     INTEGER NOT NULL,
    datetime    TIMESTAMP           DEFAULT now(),
    description VARCHAR NOT NULL,
    calories    INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


