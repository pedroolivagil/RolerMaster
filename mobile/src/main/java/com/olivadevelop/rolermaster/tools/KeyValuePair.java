package com.olivadevelop.rolermaster.tools;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class KeyValuePair {

    private Object key;
    private String label;

    public KeyValuePair(Object key, String label) {
        this.key = key;
        this.label = label;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
