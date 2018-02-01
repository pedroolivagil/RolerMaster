package com.olivadevelop.rolermaster.persistence.controllers;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class Controllers {
    private static final Controllers ourInstance = new Controllers();
    private UserController userController;

    private Controllers() {
        init();
    }

    public static Controllers getInstance() {
        return ourInstance;
    }

    public void init() {
        userController = new UserController();
    }

    public void clear() {
        userController = null;
    }

    public UserController getUserController() {
        return userController;
    }
}
