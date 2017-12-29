package com.olivadevelop.rolermaster.tools;

/**
 * Created by Oliva on 29/12/2017.
 */

public class SessionManager {
    private static final SessionManager ourInstance = new SessionManager();

    private boolean logged;

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
