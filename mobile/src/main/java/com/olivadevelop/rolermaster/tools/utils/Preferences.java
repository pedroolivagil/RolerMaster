package com.olivadevelop.rolermaster.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 11/01/2018.
 * RolerMaster
 */
public class Preferences {
    public static final String USER_PREFERENCES = "user_preferences";
    private static final Preferences ourInstance = new Preferences();
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    public static Preferences getInstance() {
        return ourInstance;
    }

    private Preferences() {
    }

    public void init(RolerMasterActivity activity) {
        prefs = activity.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor editor() {
        editor = prefs.edit();
        return editor;
    }

    public void apply() {
        editor.apply();
    }
}
