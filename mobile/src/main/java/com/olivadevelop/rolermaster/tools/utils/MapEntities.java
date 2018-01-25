package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.persistence.entities.Country;
import com.olivadevelop.rolermaster.persistence.entities.Game;
import com.olivadevelop.rolermaster.persistence.entities.GameCategory;
import com.olivadevelop.rolermaster.persistence.entities.Gender;
import com.olivadevelop.rolermaster.persistence.pojo.GenericTrans;
import com.olivadevelop.rolermaster.persistence.entities.Locale;
import com.olivadevelop.rolermaster.persistence.entities.Resource;
import com.olivadevelop.rolermaster.persistence.entities.User;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
public abstract class MapEntities {

    public static final String COMMON_FIELD_TRANS = "translation";
    static final String SERIAL_VERSION_UID = "serialVersionUID";
    static final String CHANGE_FIELD = "$change";

    private static Class GAME = Game.class;
    private static Class GAME_CATEGORY = GameCategory.class;
    private static Class GENDER = Gender.class;
    private static Class GENERIC_TRANS = GenericTrans.class;
    private static Class LOCALE = Locale.class;
    private static Class RESOURCE = Resource.class;
    private static Class USER = User.class;
    private static Class COUNTRY = Country.class;
    /*public static Class  = .class;
    public static Class  = .class;
    public static Class  = .class;
    public static Class  = .class;
    public static Class  = .class;*/

    static Class findByString(String className) {
        Class retorno = null;
        if (GAME.getSimpleName().toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = GAME;
        } else if (GAME_CATEGORY.getSimpleName().toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = GAME_CATEGORY;
        } else if (GENDER.getSimpleName().toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = GENDER;
        } else if (GENERIC_TRANS.getSimpleName().toLowerCase().equals(className.trim().toLowerCase())
                || COMMON_FIELD_TRANS.toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = GENERIC_TRANS;
        } else if (LOCALE.getSimpleName().toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = LOCALE;
        } else if (RESOURCE.getSimpleName().toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = RESOURCE;
        } else if (USER.getSimpleName().toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = USER;
        } else if (COUNTRY.getSimpleName().toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = COUNTRY;
        /*} else if (.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())){
            retorno =;*/
        }
        return retorno;
    }
}
