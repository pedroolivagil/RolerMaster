package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.managers._RestService;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.ConverterJSONArrayToList;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePair;
import com.olivadevelop.rolermaster.tools.utils.QueryBuilder;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Entity;
import com.olivadevelop.rolermaster.persistence.entities.interfaces._PersistenceMethods;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */
public class _BasicController<T> implements _PersistenceMethods<T> {

    private _RestService service;
    private Class<T> entity;
    private QueryBuilder<T> queryBuilder;
    private ConverterJSONArrayToList<T> converter;

    _BasicController(Class<T> entity) {
        this.entity = entity;
        this.queryBuilder = new QueryBuilder<>(entity);
        this.converter = new ConverterJSONArrayToList<>(entity);
    }

    public T find(Integer idEntity) throws ExecutionException, InterruptedException, JSONException {
        List<KeyValuePair> values = new ArrayList<>();
        values.add(new KeyValuePair("idEntity", String.valueOf(idEntity)));

        service = new _RestService("find_entity.php");
        service.execute(getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values));
        return converter.getNewEntity(service.get());
    }

    @Override
    public T find(FormBody query) throws ExecutionException, InterruptedException, JSONException {
        T retorno = null;
        if (Tools.isNotNull(query)) {
            service = new _RestService("find_entity.php");
            service.execute(query);
            retorno = converter.getNewEntity(service.get());
        }
        return retorno;
    }

    @Override
    public List<T> findAll() throws ExecutionException, InterruptedException, JSONException {
        return findAll(getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ALL, null));
    }

    @Override
    public List<T> findAll(FormBody query) throws ExecutionException, InterruptedException, JSONException {
        List<T> retorno = null;
        if (Tools.isNotNull(query)) {
            service = new _RestService("find_entity.php");
            service.execute(query);
            retorno = converter.getNewListEntities(service.get());
        }
        return retorno;
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

    Class<T> getEntity() {
        return entity;
    }

    QueryBuilder<T> getQueryBuilder() {
        return queryBuilder;
    }
}
