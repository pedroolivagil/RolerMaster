package com.olivadevelop.rolermaster.persistence.controllers;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class Controllers {
    private static final Controllers ourInstance = new Controllers();
    private TestController testController;
    private UserController userController;

    private Controllers() {
        testController = new TestController();
    }

    public static Controllers getInstance() {
        return ourInstance;
    }

    public TestController getTestController() {
        return testController;
    }

    public UserController getUserController() {
        return userController;
    }
}
