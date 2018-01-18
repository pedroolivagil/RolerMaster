package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.old.TestEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase creada para simular la conexión a la base de datos. En cuanto se implemente la conexión a la bbdd, las clases Test serán borradas
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class TestController extends _BasicController<TestEntity> {

    @Override
    public List<TestEntity> findAll() {
        List<TestEntity> values = new ArrayList<TestEntity>();
        values.add(new TestEntity(1, "prueba 1"));
        values.add(new TestEntity(2, "prueba 2"));
        values.add(new TestEntity(3, "prueba 3"));
        values.add(new TestEntity(4, "prueba 4"));
        return values;
    }

    /**
     * comprueba que el usuario y su password son correctos en bbdd
     *
     * @param usermail nombre de usuario o correo
     * @param pass     contraseña
     * @return boolean
     */
    public boolean testLogin(String usermail, String pass) {
        boolean retorno = true;
        return retorno;
    }
}
