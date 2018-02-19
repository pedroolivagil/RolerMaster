ALTER TABLE user_roler
    ADD CONSTRAINT userroler_master_user_fk
FOREIGN KEY (idMaster) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE user_roler
    ADD CONSTRAINT userroler_roler_user_fk
FOREIGN KEY (idRoler) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE preference
    ADD CONSTRAINT preference_user_fk
FOREIGN KEY (idUser) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE resource
    ADD CONSTRAINT resource_user_fk
FOREIGN KEY (idUser) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE user
    ADD CONSTRAINT gender_user_fk FOREIGN KEY (idGender) REFERENCES gender (idGender)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE user
    ADD CONSTRAINT country_user_fk FOREIGN KEY (idCountry) REFERENCES country (idCountry)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE gender_trans
    ADD CONSTRAINT gender_gendertrans_fk
FOREIGN KEY (idGender) REFERENCES gender (idGender)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE locale_trans
    ADD CONSTRAINT locale_localetrans_fk
FOREIGN KEY (idLocale) REFERENCES locale (idLocale)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE country
    ADD CONSTRAINT country_locale_fk
FOREIGN KEY (idLocale) REFERENCES locale (idLocale)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE country_trans
    ADD CONSTRAINT country_countrytrans_fk
FOREIGN KEY (idCountry) REFERENCES country (idCountry)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE country_trans
    ADD CONSTRAINT countrytrans_locale_fk
FOREIGN KEY (idLocale) REFERENCES locale (idLocale)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE gender_trans
    ADD CONSTRAINT gendertrans_locale_fk
FOREIGN KEY (idLocale) REFERENCES locale (idLocale)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE parameter
    ADD CONSTRAINT parameter_locale_fk
FOREIGN KEY (idLocale) REFERENCES locale (idLocale)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE game
    ADD CONSTRAINT game_gamecategory_fk
FOREIGN KEY (idCategory) REFERENCES game_category (idCategory)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE game
    ADD CONSTRAINT game_zone_resource_fk
FOREIGN KEY (zoneResource) REFERENCES resource (idResource)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE game
    ADD CONSTRAINT game_header_resource_fk
FOREIGN KEY (headerResource) REFERENCES resource (idResource)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE user_game
    ADD CONSTRAINT usergame_user_fk
FOREIGN KEY (idUser) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE user_game
    ADD CONSTRAINT usergame_game_fk
FOREIGN KEY (idGame) REFERENCES game (idGame)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE game_resource
    ADD CONSTRAINT gameresource_game_fk
FOREIGN KEY (idGame) REFERENCES game (idGame)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE game_resource
    ADD CONSTRAINT resource_game_fk
FOREIGN KEY (idResource) REFERENCES resource (idResource)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE game_category
    ADD CONSTRAINT gamecategory_user_fk
FOREIGN KEY (idUser) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE character_game
    ADD CONSTRAINT charactergame_game_fk
FOREIGN KEY (idGame) REFERENCES game (idGame)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE character_game
    ADD CONSTRAINT charactergame_character_fk
FOREIGN KEY (idCharacter) REFERENCES `character` (idCharacter)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE character_user
    ADD CONSTRAINT characteruser_user_fk
FOREIGN KEY (idUser) REFERENCES user (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE character_user
    ADD CONSTRAINT characteruser_character_fk
FOREIGN KEY (idCharacter) REFERENCES `character` (idCharacter)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

