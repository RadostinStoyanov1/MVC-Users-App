CREATE TABLE IF NOT EXISTS master_users (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    uuid binary(16),
    email varchar(255) DEFAULT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

DELETE from master_users;

INSERT INTO master_users (id, uuid, username, email, first_name, last_name, password)
VALUES
    (1, '7706c6c6-af4a-4f23-a799-b83355befb84', 'Rado', 'rado@rado.com','Radostin','Stoyanov','0c8f33dfcfc3402f797ae7ca1cc11a8fcfdbd644c21cc132c7b776d5e3ebe4e104731fd7cc9785f77cb3e8af2db641b7');

CREATE TABLE IF NOT EXISTS roles (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    role enum('ADMIN','USER') DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role)
VALUES (2, 'USER');

CREATE TABLE IF NOT EXISTS users_roles (
    user_id bigint(20) NOT NULL,
    role_id bigint(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES master_users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),
    (1, 2);