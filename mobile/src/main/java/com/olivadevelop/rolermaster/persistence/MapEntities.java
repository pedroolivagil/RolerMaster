package com.olivadevelop.rolermaster.persistence;

import com.olivadevelop.rolermaster.persistence.entities.Character;
import com.olivadevelop.rolermaster.persistence.entities.Country;
import com.olivadevelop.rolermaster.persistence.entities.Game;
import com.olivadevelop.rolermaster.persistence.entities.GameCategory;
import com.olivadevelop.rolermaster.persistence.entities.Gender;
import com.olivadevelop.rolermaster.persistence.entities.GenericTrans;
import com.olivadevelop.rolermaster.persistence.entities.Locale;
import com.olivadevelop.rolermaster.persistence.entities.Notification;
import com.olivadevelop.rolermaster.persistence.entities.Parameter;
import com.olivadevelop.rolermaster.persistence.entities.Preference;
import com.olivadevelop.rolermaster.persistence.entities.Resource;
import com.olivadevelop.rolermaster.persistence.entities.User;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Knowledge;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Mental;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Merit;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Physical;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Power;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Social;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Talent;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Technique;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Undertone;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Virtue;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Weapon;
import com.olivadevelop.rolermaster.persistence.pojo.NotificationPriority;
import com.olivadevelop.rolermaster.persistence.pojo.TypeResource;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
public abstract class MapEntities {

    private static final String COMMON_FIELD_TRANS = "translation";
    private static final String COMMON_FIELD_PRIORITY = "priority";

    private static Class GAME = Game.class;
    private static Class GAME_CATEGORY = GameCategory.class;
    private static Class GENDER = Gender.class;
    private static Class GENERIC_TRANS = GenericTrans.class;
    private static Class LOCALE = Locale.class;
    private static Class RESOURCE = Resource.class;
    private static Class USER = User.class;
    private static Class COUNTRY = Country.class;
    private static Class NOTIFICATION = Notification.class;
    private static Class PREFERENCE = Preference.class;
    private static Class NOTIFICATION_PRIORITY = NotificationPriority.class;
    private static Class TYPE_RESOURCE = TypeResource.class;
    private static Class CHARACTER = Character.class;
    private static Class PARAMETER = Parameter.class;
    private static Class KNOWLEDGE = Knowledge.class;
    private static Class MENTAL = Mental.class;
    private static Class MERIT = Merit.class;
    private static Class PHYSICAL = Physical.class;
    private static Class POWER = Power.class;
    private static Class SOCIAL = Social.class;
    private static Class TALENT = Talent.class;
    private static Class TECHNIQUE = Technique.class;
    private static Class UNDERTONE = Undertone.class;
    private static Class VIRTUE = Virtue.class;
    private static Class WEAPON = Weapon.class;

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
        } else if (NOTIFICATION.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = NOTIFICATION;
        } else if (NOTIFICATION_PRIORITY.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())
                || COMMON_FIELD_PRIORITY.toLowerCase().equals(className.trim().toLowerCase())) {
            retorno = NOTIFICATION_PRIORITY;
        } else if (PREFERENCE.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = PREFERENCE;
        } else if (TYPE_RESOURCE.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = TYPE_RESOURCE;
        } else if (CHARACTER.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = CHARACTER;
        } else if (PARAMETER.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = PARAMETER;
        } else if (KNOWLEDGE.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = KNOWLEDGE;
        } else if (MENTAL.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = MENTAL;
        } else if (MERIT.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = MERIT;
        } else if (PHYSICAL.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = PHYSICAL;
        } else if (POWER.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = POWER;
        } else if (SOCIAL.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = SOCIAL;
        } else if (TALENT.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = TALENT;
        } else if (TECHNIQUE.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = TECHNIQUE;
        } else if (UNDERTONE.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = UNDERTONE;
        } else if (VIRTUE.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = VIRTUE;
        } else if (WEAPON.getSimpleName().toLowerCase().trim().equals(className.trim().toLowerCase())) {
            retorno = WEAPON;
        }
        return retorno;
    }
}
