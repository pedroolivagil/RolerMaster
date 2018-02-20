package com.olivadevelop.rolermaster.tools.persistence.controllers;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.persistence.interfaces._PersistenceMethods;
import com.olivadevelop.rolermaster.tools.persistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.tools.persistence.managers.ServiceURL;
import com.olivadevelop.rolermaster.tools.persistence.utils.ConverterJSONArrayToList;
import com.olivadevelop.rolermaster.tools.persistence.utils.KeyValuePair;
import com.olivadevelop.rolermaster.tools.persistence.utils.QueryBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */
public class _BasicController<T> implements _PersistenceMethods<T> {

    private Class<T> entity;
    private QueryBuilder<T> queryBuilder;
    private ConverterJSONArrayToList<T> converter;

    public _BasicController(Class<T> entity) {
        this.entity = entity;
        this.queryBuilder = new QueryBuilder<>(entity);
        this.converter = new ConverterJSONArrayToList<>(entity);
    }

    public T find(Integer idEntity) throws ExecutionException, InterruptedException, JSONException {
        List<KeyValuePair<String, ?>> values = new ArrayList<>();
        values.add(new KeyValuePair<>("idEntity", idEntity));
        JSONObject result = ServiceDAO.getInstance().newCall(ServiceURL.READ, getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values));
        return converter.getNewEntity(result);
    }

    @Override
    public T find(FormBody query) throws ExecutionException, InterruptedException, JSONException {
        T retorno = null;
        if (Tools.isNotNull(query)) {
            JSONObject result = ServiceDAO.getInstance().newCall(ServiceURL.READ, query);
            retorno = converter.getNewEntity(result);
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
            JSONObject result = ServiceDAO.getInstance().newCall(ServiceURL.READ, query);
            retorno = converter.getNewListEntities(result);
        }
        return retorno;
    }

    @Override
    public List<T> findAllByIds(List<Integer> idsEntity) {
        return null;
    }

    @Override
    public T findOneByEntity(T entity) {
        return null;
    }

    @Override
    public List<T> findByEntity(T entity) {
        return null;
    }

    @Override
    public boolean persist(T entity) throws ExecutionException, InterruptedException, JSONException {
        JSONObject result = ServiceDAO.getInstance().newCall(ServiceURL.CREATE, getQueryBuilder().insertQuery(entity));
        converter.getNewEntity(result);
        return false;
    }

    @Override
    public boolean merge(T entity) throws ExecutionException, InterruptedException, JSONException {
        return false;
    }

    @Override
    public boolean remove(T entity) throws ExecutionException, InterruptedException, JSONException {
        return false;
    }

    protected Class<T> getEntity() {
        return entity;
    }

    protected QueryBuilder<T> getQueryBuilder() {
        return queryBuilder;
    }
}
