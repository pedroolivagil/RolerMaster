-- relaciones con el usuario
ALTER TABLE user_roler
    ADD CONSTRAINT user_userroler_fk1 FOREIGN KEY (idMaster) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE user_roler
    ADD CONSTRAINT user_userroler_fk2 FOREIGN KEY (idRoler) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE game_category
    ADD CONSTRAINT user_gameCategory_fk1 FOREIGN KEY (idUser) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE resource
    ADD CONSTRAINT user_resource_fk1 FOREIGN KEY (idUser) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE preference
    ADD CONSTRAINT user_preference_fk1 FOREIGN KEY (idUser) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE user
    ADD CONSTRAINT gender_user_fk1 FOREIGN KEY (idGender) REFERENCES gender (idGender)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE user
    ADD CONSTRAINT country_user_fk1 FOREIGN KEY (idCountry) REFERENCES country (idCountry)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- relaciones con LOCALE
ALTER TABLE generic_trans
    ADD CONSTRAINT generictrans_locale_idLocale_fk
FOREIGN KEY (idLocale) REFERENCES rolermaster.locale (idLocale)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE parameter
    ADD CONSTRAINT parameter_locale_idLocale_fk
FOREIGN KEY (idLocale) REFERENCES rolermaster.locale (idLocale)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE country
    ADD CONSTRAINT country_locale_idLocale_fk
FOREIGN KEY (idLocale) REFERENCES rolermaster.locale (idLocale)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- relaciones con GENERIC_TRANS
ALTER TABLE country
    ADD CONSTRAINT country_generictrans_idTrans_fk
FOREIGN KEY (idTrans) REFERENCES rolermaster.generic_trans (idGenericTrans)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE gender
    ADD CONSTRAINT gender_generictrans_idTrans_fk
FOREIGN KEY (idTrans) REFERENCES rolermaster.generic_trans (idGenericTrans)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE parameter
    ADD CONSTRAINT parameter_generictrans_idTrans_fk
FOREIGN KEY (idTrans) REFERENCES rolermaster.generic_trans (idGenericTrans)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

