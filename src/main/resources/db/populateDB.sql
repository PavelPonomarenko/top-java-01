DELETE
FROM user_roles;
DELETE
FROM users;

INSERT INTO users(id, name, email, password)
VALUES (1, 'User', 'user@gamil.com', 'password'),
       (2, 'Admin', 'admin@gamil.com', 'admin');

INSERT INTO user_roles(user_id, role)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');