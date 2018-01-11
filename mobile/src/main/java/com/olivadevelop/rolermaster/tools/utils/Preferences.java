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

    private Preferences() {
    }

    public static Preferences getInstance() {
        return ourInstance;
    }

    public static SharedPreferences getPrefs() {
        return getInstance().prefs;
    }

    public void init(RolerMasterActivity activity) {
        prefs = activity.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor editor() {
        return prefs.edit();
    }

    public SharedPreferences.Editor remove(String key) {
        return editor().remove(key);
    }

    public SharedPreferences.Editor clear() {
        return editor().clear();
    }

    public void apply() {
        editor().apply();
    }
}
