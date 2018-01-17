package com.olivadevelop.rolermaster.persistence.managers;

import com.olivadevelop.rolermaster.persistence.entities.Entity;
import com.olivadevelop.rolermaster.tools.Tools;

import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class _PersistenceManager<T> implements _PersistenceMethods<T> {

    private static _PersistenceManager instance = new _PersistenceManager();

    private _PersistenceManager() {
    }

    public static _PersistenceManager getInstance() {
        if (Tools.isNull(instance)) {
            instance = new _PersistenceManager();
        }
        return instance;
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
