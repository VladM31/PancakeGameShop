CREATE TABLE authentication_code
(
    id               bigint   NOT NULL AUTO_INCREMENT,
    value            char(30) NOT NULL,
    date_of_creation datetime NOT NULL,
    active           bool     NOT NULL,
    users_table_id   bigint   NOT NULL,
    CONSTRAINT authentication_code_pk PRIMARY KEY (id)
);


CREATE TABLE roles_table
(
    id   int          NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    UNIQUE INDEX role_table_ak_1 (name),
    CONSTRAINT roles_table_pk PRIMARY KEY (id)
);

CREATE TABLE users_table
(
    id                bigint                              NOT NULL AUTO_INCREMENT,
    phone_number      varchar(15)                         NOT NULL,
    password          varchar(60)                         NOT NULL,
    nickname          varchar(255)                        NOT NULL,
    first_name        varchar(60)                         NOT NULL,
    last_name         varchar(60)                         NOT NULL,
    birth_date        date                                NOT NULL,
    active            bool                                NOT NULL,
    selected_currency varchar(3)                          NOT NULL,
    user_state        enum ('REGISTRATION', 'REGISTERED') NOT NULL,
    email             varchar(60)                         NULL,
    role_table_id     int                                 NOT NULL,
    CONSTRAINT users_table_pk PRIMARY KEY (id)
);

ALTER TABLE authentication_code
    ADD CONSTRAINT authentication_code_users_table FOREIGN KEY authentication_code_users_table (users_table_id)
        REFERENCES users_table (id);

ALTER TABLE users_table
    ADD CONSTRAINT user_table_role_table FOREIGN KEY user_table_role_table (role_table_id)
        REFERENCES roles_table (id);