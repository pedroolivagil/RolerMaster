-- Tablas
CREATE TABLE user (
    idUser     INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(40)           NOT NULL,
    pass       VARCHAR(32)           NOT NULL,
    email      VARCHAR(255)          NOT NULL,
    name       VARCHAR(50)           NOT NULL,
    lastname   VARCHAR(150),
    idCountry  INT(10) UNSIGNED      NOT NULL,
    phone      VARCHAR(16),
    birthdate  DATE                  NOT NULL,
    idGender   INTEGER UNSIGNED      NOT NULL,
    isMaster   BOOLEAN               NOT NULL,
    flagActive BOOLEAN DEFAULT TRUE  NOT NULL,
    flagStatus BOOLEAN DEFAULT FALSE NOT NULL
);
CREATE UNIQUE INDEX user_username_uindex
    ON user (username);
CREATE UNIQUE INDEX user_email_uindex
    ON user (email);
ALTER TABLE user
    ADD INDEX (idCountry);
ALTER TABLE user
    ADD INDEX (idGender);

CREATE TABLE user_roler (
    idMaster INT(10) UNSIGNED,
    idRoler  INT(10) UNSIGNED,
    date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT userroler_user_fk PRIMARY KEY (idMaster, idRoler)
);

CREATE TABLE generic_trans (
    idGenericTrans INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    idLocale       INT UNSIGNED NOT NULL,
    text           VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX generictrans_idLocale_uindex
    ON generic_trans (idLocale, text);

CREATE TABLE country (
    idCountry INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    code      VARCHAR(3)       NOT NULL,
    idTrans   INT(10) UNSIGNED NOT NULL,
    idLocale  INT(10) UNSIGNED NOT NULL
);
CREATE UNIQUE INDEX country_codeISO_uindex
    ON country (code);

CREATE TABLE locale (
    idLocale INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    codeISO  INT(10)          NOT NULL,
    idTrans  INT(10) UNSIGNED NOT NULL
);
CREATE UNIQUE INDEX locale_codeISO_uindex
    ON locale (codeISO);

CREATE TABLE gender (
    idGender INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    code     VARCHAR(3)       NOT NULL,
    idTrans  INT(10) UNSIGNED NOT NULL
);
CREATE UNIQUE INDEX gender_code_uindex
    ON gender (code);

CREATE TABLE resource (
    idResource INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    code       VARCHAR(20)      NOT NULL,
    type       TINYINT(1)       NOT NULL,
    file       TEXT             NOT NULL,
    date       TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
    idUser     INT(10) UNSIGNED NOT NULL
);
CREATE UNIQUE INDEX resource_code_uindex
    ON resource (code);

CREATE TABLE preference (
    idPrefs INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    idUser  INT(10) UNSIGNED NOT NULL,
    p_key   VARCHAR(20)      NOT NULL,
    p_value VARCHAR(150)     NOT NULL,
    date    TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX preference_idUser_uindex
    ON preference (idUser, p_key);

CREATE TABLE game_category (
    idCategory INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    idUser     INT(10) UNSIGNED NOT NULL,
    name       VARCHAR(200)     NOT NULL
);
CREATE UNIQUE INDEX game_category_name_uindex
    ON game_category (name, idUser);

CREATE TABLE parameter (
    idParameter INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    category    VARCHAR(255)     NOT NULL,
    idLocale    INT(10) UNSIGNED NOT NULL,
    idTrans     INT(10) UNSIGNED NOT NULL
);
CREATE UNIQUE INDEX parameter_idTrans_uindex
    ON parameter (idTrans, idLocale, category);
