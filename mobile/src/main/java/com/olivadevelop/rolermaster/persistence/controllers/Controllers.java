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
        init();
    }

    public void init(){
        testController = new TestController();
        userController = new UserController();
    }

    public void clear(){
        testController = null;
        userController = null;
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
