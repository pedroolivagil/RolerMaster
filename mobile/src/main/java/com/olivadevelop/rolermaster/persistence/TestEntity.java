package com.olivadevelop.rolermaster.persistence;

/**
 * Created by Oliva on 02/01/2018.
 */

public class TestEntity implements Entity {

    private String texto;

    public TestEntity(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
