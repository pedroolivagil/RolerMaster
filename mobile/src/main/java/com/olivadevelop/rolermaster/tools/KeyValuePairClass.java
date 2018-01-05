package com.olivadevelop.rolermaster.tools;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class KeyValuePairClass {

    private Class key;
    private boolean include;

    public KeyValuePairClass(Class key, boolean include) {
        this.key = key;
        this.include = include;
    }

    public Class getKey() {
        return key;
    }

    public void setKey(Class key) {
        this.key = key;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }
}
