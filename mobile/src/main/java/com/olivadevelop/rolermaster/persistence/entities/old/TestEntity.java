package com.olivadevelop.rolermaster.persistence.entities.old;

import org.bson.conversions.Bson;

/**
 * Created by Oliva on 02/01/2018.
 */

public class TestEntity implements Entity {

    public static final String FIELD_KEY = "key";
    public static final String FIELD_TEXTO = "texto";

    private Integer key;
    private String texto;

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
    public Bson toBson() {
        return null;
    }
}