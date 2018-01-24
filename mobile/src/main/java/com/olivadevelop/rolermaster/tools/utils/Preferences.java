package com.olivadevelop.rolermaster.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 11/01/2018.
 * RolerMaster
 */
public class Preferences {
    private static final String USER_PREFERENCES = "user_preferences";
    private static final Preferences ourInstance = new Preferences();
    private SharedPreferences prefs;

    /**
     * Sincronizar las preferencias con la base de datos.
     */
    private Preferences() {

    }

    public static Preferences getInstance() {
        return ourInstance;
    }

    public static SharedPreferences getPrefs() {
        return getInstance().prefs;
    }

    void init(RolerMasterActivity activity) {
        prefs = activity.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor editor() {
        return prefs.edit();
    }

    public void remove(String key) {
        editor().remove(key).commit();
    }

    public boolean clear() {
        return editor().clear().commit();
    }

    public void apply() {
        editor().apply();
    }

    public boolean commit() {
        return editor().commit();
    }


    public abstract static class EnumBundle {
        public static final String FORGOT_PASS_EMAIL = "email";
        public static final String LOGIN_EMAIL = "loginEmail"; //borrar

        public static final String PREFS_AUTOLOGIN = "autologin";
        public static final String PREFS_NOTIF_EMAIL = "notifEmail";
        public static final String PREFS_NOTIF_CALENDAR = "notifCalendar";
        public static final String PREFS_NOTIF_SWATCH = "notifSwatch";
        public static final String PREFS_NOTIF_ROLERMASTER = "notifApp";
        public static final String PREFS_EXPORT_PDF = "exportPdf";

        public static final String SESSION_ID_USER = "sessionIdUser";
        public static final String SESSION_USERNAME = "sessionUserName";
        public static final String SESSION_USERPASS = "sessionUserPass";
    }
}
