CREATE TABLE promo_code
(
    id                  bigint   NOT NULL AUTO_INCREMENT,
    discount_percentage int      NOT NULL,
    end_date            datetime NOT NULL,
    CONSTRAINT promo_code_pk PRIMARY KEY (id)
);