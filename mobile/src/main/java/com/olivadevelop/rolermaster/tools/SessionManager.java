package com.olivadevelop.rolermaster.tools;

import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.persistence.controllers.UserController;
import com.olivadevelop.rolermaster.persistence.entities.User;
import com.olivadevelop.rolermaster.tools.utils.EnumBundle;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 29/12/2017.
 * RolerMaster
 */

public class SessionManager {
    private static final SessionManager ourInstance = new SessionManager();

    private UserController controller;
    private boolean logged;

    private SessionManager() {
        controller = Controllers.getInstance().getUserController();
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
            User user = controller.find(usermail);
            if (user.getPassword().equals(pass)) {
                Preferences.getInstance().editor().putInt(EnumBundle.SESSION_ID_USER, user.getIdUser());
                Preferences.getInstance().editor().putString(EnumBundle.SESSION_USERNAME, usermail);
                retorno = true;
            }
        }
        return retorno;
    }
}
