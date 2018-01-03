package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.TestEntity;

import java.util.ArrayList;
import java.util.List;

/**
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
}
