package com.olivadevelop.rolermaster.persistence.controllers;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class Controllers {
    private static final Controllers ourInstance = new Controllers();

    public static Controllers getInstance() {
        return ourInstance;
    }

    private TestController testController;

    private Controllers() {
        testController = new TestController();
    }

    public TestController getTestController() {
        return testController;
    }
}
