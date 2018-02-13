package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.interfaces.RelatedEntity;
import com.olivadevelop.rolermaster.persistence.entities.interfaces._PersistenceMethods;
import com.olivadevelop.rolermaster.persistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.ConverterJSONArrayToList;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePair;
import com.olivadevelop.rolermaster.tools.utils.QueryBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
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

    _BasicController(Class<T> entity) {
        this.entity = entity;
        this.queryBuilder = new QueryBuilder<>(entity);
        this.converter = new ConverterJSONArrayToList<>(entity);
    }

    public T find(Integer idEntity) throws ExecutionException, InterruptedException, JSONException {
        List<KeyValuePair> values = new ArrayList<>();
        values.add(new KeyValuePair("idEntity", String.valueOf(idEntity)));
        JSONObject result = ServiceDAO.getInstance().newCall("read.php", getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values));
        return converter.getNewEntity(result);
    }

    @Override
    public T find(FormBody query) throws ExecutionException, InterruptedException, JSONException {
        T retorno = null;
        if (Tools.isNotNull(query)) {
            JSONObject result = ServiceDAO.getInstance().newCall("read.php", query);
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
            JSONObject result = ServiceDAO.getInstance().newCall("read.php", query);
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
        List<KeyValuePair> values = new ArrayList<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        values.add(new KeyValuePair("entity[]", entity));
        for (Field field : fields) {
            field.setAccessible(true);
            RelatedEntity relEnt = field.getAnnotation(RelatedEntity.class);
            if (Tools.isNotNull(relEnt)) {
                try {
                    values.add(new KeyValuePair("entity[]", field.get(field.getDeclaringClass())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            field.setAccessible(false);
        }
        JSONObject result = ServiceDAO.getInstance().newCall("create.php", getQueryBuilder().insertQuery(values));
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

    Class<T> getEntity() {
        return entity;
    }

    QueryBuilder<T> getQueryBuilder() {
        return queryBuilder;
    }
}
