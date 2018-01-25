package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.persistence.entities.Country;
import com.olivadevelop.rolermaster.persistence.entities.Game;
import com.olivadevelop.rolermaster.persistence.entities.GameCategory;
import com.olivadevelop.rolermaster.persistence.entities.Gender;
import com.olivadevelop.rolermaster.persistence.entities.GenericTrans;
import com.olivadevelop.rolermaster.persistence.entities.Locale;
import com.olivadevelop.rolermaster.persistence.entities.Resource;
import com.olivadevelop.rolermaster.persistence.entities.User;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
public abstract class MapEntities {

    public static Class GAME = Game.class;
    public static Class GAME_CATEGORY = GameCategory.class;
    public static Class GENDER = Gender.class;
    public static Class GENERIC_TRANS = GenericTrans.class;
    public static Class LOCALE = Locale.class;
    public static Class RESOURCE = Resource.class;
    public static Class USER = User.class;
    public static Class COUNTRY = Country.class;
    /*public static Class  = .class;
    public static Class  = .class;
    public static Class  = .class;
    public static Class  = .class;
    public static Class  = .class;*/

    public static Class findByString(String className) {
        Class retorno = null;
        if (GAME.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = GAME;
        } else if (GAME_CATEGORY.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = GAME_CATEGORY;
        } else if (GENDER.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = GENDER;
        } else if (GENERIC_TRANS.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = GENERIC_TRANS;
        } else if (LOCALE.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = LOCALE;
        } else if (RESOURCE.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = RESOURCE;
        } else if (USER.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = USER;
        } else if (COUNTRY.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = COUNTRY;
        /*} else if (.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())){
            retorno =;*/
        }
        return retorno;
    }
}
