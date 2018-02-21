-- Tablas
CREATE TABLE user (
    idUser     INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(40)           NOT NULL,
    pass       VARCHAR(32)           NOT NULL,
    email      VARCHAR(255)          NOT NULL,
    name       VARCHAR(50)           NOT NULL,
    lastname   VARCHAR(150),
    idCountry  INT(10) UNSIGNED,
    phone      VARCHAR(16),
    birthdate  DATE                  NOT NULL,
    idGender   INTEGER UNSIGNED,
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
    dateTime     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT userroler_pk PRIMARY KEY (idMaster, idRoler)
);

CREATE TABLE country (
    idCountry INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    code      VARCHAR(3)       NOT NULL,
    idLocale  INT(10) UNSIGNED
);
CREATE UNIQUE INDEX country_codeISO_uindex
    ON country (code);

CREATE TABLE country_trans (
    idTrans  INT(10) UNSIGNED  PRIMARY KEY AUTO_INCREMENT,
    idCountry INT(10) UNSIGNED,
    idLocale INT(10) UNSIGNED,
    text     VARCHAR(255)     NOT NULL
);
CREATE UNIQUE INDEX countrytrans_country_uindex
      ON country_trans (idCountry,idLocale,text);

CREATE TABLE localeGroup (
    idLocale INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    codeISO  VARCHAR(3)       NOT NULL
);
CREATE UNIQUE INDEX locale_codeISO_uindex
    ON localeGroup (codeISO);

CREATE TABLE locale_trans (
    idTrans  INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    idLocale INT(10) UNSIGNED,
    idLocaleGroup INT(10) UNSIGNED,
    text     VARCHAR(255)     NOT NULL
);
CREATE UNIQUE INDEX localetrans_locale_uindex
      ON locale_trans (idLocale,idLocaleGroup,text);

CREATE TABLE resource (
    idResource INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    code       VARCHAR(20)      NOT NULL,
    type       TINYINT(1)       NOT NULL,
    file       TEXT             NOT NULL,
    date       TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
    idUser     INT(10) UNSIGNED
);
CREATE UNIQUE INDEX resource_code_uindex
    ON resource (code);

CREATE TABLE preference (
    idPrefs INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    idUser  INT(10) UNSIGNED,
    p_key   VARCHAR(20)      NOT NULL,
    p_value VARCHAR(150)     NOT NULL,
    date    TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX preference_idUser_uindex
    ON preference (idUser, p_key);

CREATE TABLE game_category (
    idCategory INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    idUser     INT(10) UNSIGNED,
    name       VARCHAR(200)     NOT NULL
);
CREATE UNIQUE INDEX game_category_name_uindex
    ON game_category (name, idUser);

CREATE TABLE gender (
    idGender INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    code     VARCHAR(3)       NOT NULL
);
CREATE UNIQUE INDEX gender_code_uindex
    ON gender (code);

CREATE TABLE gender_trans (
    idTrans  INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    idGender INT(10) UNSIGNED,
    idLocale INT(10) UNSIGNED,
    text     VARCHAR(255)     NOT NULL
);
CREATE UNIQUE INDEX gendertrans_gender_uindex
      ON gender_trans (idGender,idLocale,text);

CREATE TABLE parameter (
    idParameter INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    category    VARCHAR(255)     NOT NULL,
    idLocale    INT(10) UNSIGNED,
    text        VARCHAR(255)     NOT NULL
);
CREATE UNIQUE INDEX parameter_idTrans_uindex
    ON parameter (idLocale, category, text);

CREATE TABLE game (
    idGame         INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(100)                        NOT NULL,
    code           VARCHAR(20)                         NOT NULL,
    maxCharacters  INT(5)                              NOT NULL,
    date           TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    zoneResource   INT(10) UNSIGNED                    NOT NULL,
    headerResource INT(10) UNSIGNED                    NOT NULL,
    status         TINYINT(1) DEFAULT 0                NOT NULL,
    location       VARCHAR(255)                        NOT NULL,
    idCategory     INT(10) UNSIGNED
);
CREATE UNIQUE INDEX game_name_uindex
    ON game (name);
CREATE UNIQUE INDEX game_code_uindex
    ON game (code);

CREATE TABLE user_game (
    idGame INT(10) UNSIGNED NOT NULL,
    idUser INT(10) UNSIGNED NOT NULL,
    date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT usergame_pk PRIMARY KEY (idGame, idUser)
);

CREATE TABLE game_resource (
    idResource INT(10) UNSIGNED NOT NULL,
    idGame     INT(10) UNSIGNED NOT NULL,
    date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT gameresource_pk PRIMARY KEY (idGame, idResource)
);

CREATE TABLE `character` (
    idCharacter INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255)     NOT NULL,
    surname     VARCHAR(255)     NOT NULL,
    lastname    VARCHAR(255)     NOT NULL,
    birthdate   DATE             NOT NULL,
    planet      VARCHAR(255)     NOT NULL,
    idGender    INT(10) UNSIGNED,
    chronicle   VARCHAR(255)     NOT NULL,
    behaviour   INT(10)          NOT NULL,
    `character` INT(10)          NOT NULL,
    concept     INT(10)          NOT NULL,
    humanity    INT(10)          NOT NULL    DEFAULT 1,
    will        INT(10)          NOT NULL    DEFAULT 1,
    faith       INT(10)          NOT NULL    DEFAULT 1,
    health      INT(10)          NOT NULL    DEFAULT 1,
    experience  VARCHAR(255)     NULL
);

CREATE TABLE character_game (
    idGame      INT(10) UNSIGNED NOT NULL,
    idCharacter INT(10) UNSIGNED NOT NULL,
    date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT charactergame_pk PRIMARY KEY (idGame, idCharacter)
);

CREATE TABLE character_user (
    idUser      INT(10) UNSIGNED NOT NULL,
    idCharacter INT(10) UNSIGNED NOT NULL,
    date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT charactergame_pk PRIMARY KEY (idUser, idCharacter)
);