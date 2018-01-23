package com.olivadevelop.rolermaster.tools;

import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.persistence.entities.old.User;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 29/12/2017.
 * RolerMaster
 */

public class SessionManager {
    private static final SessionManager ourInstance = new SessionManager();

    private boolean logged;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return ourInstance;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean login(String usermail, String pass) {
        boolean retorno = false;
        if (Tools.isNotNull(usermail) && Tools.isNotNull(pass)) {
            User user = Controllers.getInstance().getUserController().read(usermail, null);
            if (user != null && user.getPassword().equals(pass)) {
                Preferences.getInstance().editor().putInt(Preferences.EnumBundle.SESSION_ID_USER, user.getIdUser()).apply();
                Preferences.getInstance().editor().putString(Preferences.EnumBundle.SESSION_USERPASS, user.getPassword()).apply();
                Preferences.getInstance().editor().putString(Preferences.EnumBundle.SESSION_USERNAME, user.getName()).apply();
                setLogged(true);
                retorno = true;
            }
        }
        return retorno;
    }

    public void logout() {
        setLogged(false);
        Preferences.getInstance().clear();
        Preferences.getInstance().apply();
    }

    public void autologin() {
        if (Preferences.getPrefs().getBoolean(Preferences.EnumBundle.PREFS_AUTOLOGIN, false)) {
            String user = Preferences.getPrefs().getString(Preferences.EnumBundle.SESSION_USERNAME, null);
            String pass = Preferences.getPrefs().getString(Preferences.EnumBundle.SESSION_USERPASS, null);
            login(user, pass);
        } else {
            logout();
        }
    }
}
