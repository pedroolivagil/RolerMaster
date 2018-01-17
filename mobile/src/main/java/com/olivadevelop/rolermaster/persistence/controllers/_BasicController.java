package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.Entity;
import com.olivadevelop.rolermaster.persistence.managers._PersistenceManager;
import com.olivadevelop.rolermaster.persistence.managers._PersistenceMethods;
import com.olivadevelop.rolermaster.tools.Tools;

import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class _BasicController<T> implements _PersistenceMethods<T> {

    private _PersistenceManager manager = _PersistenceManager.getInstance();

    protected _BasicController() {
    }

    public _PersistenceManager pm() {
        if (Tools.isNull(manager)) {
            manager = _PersistenceManager.getInstance();
        }
        return manager;
    }

    @Override
    public T find(Integer idEntity) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findAllByIds(List<Integer> idsEntity) {
        return null;
    }

    @Override
    public T findOneByEntity(Entity entity) {
        return null;
    }

    @Override
    public List<T> findByEntity(Entity entity) {
        return null;
    }

    @Override
    public boolean persist(Entity entity) {
        return false;
    }

    @Override
    public boolean merge(Entity entity) {
        return false;
    }

    @Override
    public boolean remove(Entity entity) {
        return false;
    }
}
