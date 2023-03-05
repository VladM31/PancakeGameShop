CREATE TABLE user
(
    chat_id           BIGINT      NOT NULL PRIMARY KEY,
    number            VARCHAR(15) NOT NULL UNIQUE,
    date_registration DATETIME    NOT NULL,
    active            BOOLEAN     NOT NULL
);
