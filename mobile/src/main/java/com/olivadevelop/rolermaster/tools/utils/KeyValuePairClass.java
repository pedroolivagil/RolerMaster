package com.olivadevelop.rolermaster.tools.utils;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class KeyValuePairClass {

    private Class key;
    private boolean included;

    public KeyValuePairClass(Class key, boolean included) {
        this.key = key;
        this.included = included;
    }

    public Class getKey() {
        return key;
    }

    public void setKey(Class key) {
        this.key = key;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }
}
