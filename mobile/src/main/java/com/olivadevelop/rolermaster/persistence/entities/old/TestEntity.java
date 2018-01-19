package com.olivadevelop.rolermaster.persistence.entities.old;


import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

public class TestEntity extends BasicEntity {

    public static final String FIELD_KEY = "key";
    public static final String FIELD_TEXTO = "texto";

    private Integer key;
    private String texto;

    public TestEntity() {
    }

    public TestEntity(JSONObject json) throws JSONException {
        fillEntity(json);
    }

    public TestEntity(Integer key, String texto) {
        this.key = key;
        this.texto = texto;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public void fillEntity(JSONObject json) throws JSONException {
        if (Tools.isNotNull(json)) {
            this.setKey(json.getInt(FIELD_KEY));
            this.setTexto(json.getString(FIELD_TEXTO));
        }
    }
}