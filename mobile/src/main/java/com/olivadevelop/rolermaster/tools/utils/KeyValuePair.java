package com.olivadevelop.rolermaster.tools.utils;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class KeyValuePair {

    private Object key;
    private Object label;

    public KeyValuePair(Object key, String label) {
        this.key = key;
        this.label = label;
    }

    public KeyValuePair(Object key, Object label) {
        this(key, String.valueOf(label));
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getLabel() {
        return label;
    }

    public String getLabelString() {
        return String.valueOf(label);
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
