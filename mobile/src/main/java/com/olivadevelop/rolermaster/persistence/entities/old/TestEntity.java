package com.olivadevelop.rolermaster.persistence.entities.old;


import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

public class TestEntity extends BasicEntity {

    public static final String FIELD_KEY = "key";
    public static final String FIELD_TEXTO = "text";

    private Integer key;
    private String text;

    public TestEntity() {
    }

    public TestEntity(JSONObject json) throws JSONException {
        super(json);
    }

    public TestEntity(Integer key, String texto) {
        this.key = key;
        this.text = texto;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void onConstruct(JSONObject json) throws JSONException {
        this.setKey(json.getInt(FIELD_KEY));
        this.setText(json.getString(FIELD_TEXTO));
    }
}