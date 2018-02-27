package com.olivadevelop.rolermaster.olivaobjectpersistence.controllers;

import android.util.Log;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces._PersistenceMethods;
import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceURL;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.KeyValuePair;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.OlivaDevelopException;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.QueryBuilder;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Utils;
import com.olivadevelop.rolermaster.tools.Tools;

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
public class _BasicController<T extends _BasicEntity> implements _PersistenceMethods<T> {

    private Class<T> entity;
    private QueryBuilder<T> queryBuilder;
    private _SequenceController<T> _sequenceController;

    public _BasicController(Class<T> entity) {
        this.entity = entity;
        this.queryBuilder = new QueryBuilder<>(entity);
        this._sequenceController = new _SequenceController<>(entity);
    }

    public T find(Integer idEntity) throws ExecutionException, InterruptedException, JSONException {
        List<KeyValuePair<String, ?>> values = new ArrayList<>();
        values.add(new KeyValuePair<>("idEntity", idEntity));/*
        JSONObject result = ServiceDAO.getInstance().newCall(ServiceURL.READ, );
        return this.queryBuilder.getJsonPersistence().getEntity(result);*/
        return find(getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values));
    }

    @Override
    public T find(FormBody query) throws ExecutionException, InterruptedException, JSONException {
        T retorno = null;
        if (Tools.isNotNull(query)) {
            JSONObject result = ServiceDAO.getInstance().newCall(ServiceURL.READ, query);
            retorno = this.queryBuilder.getJsonPersistence().getEntity(result);
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
            retorno = this.queryBuilder.getJsonPersistence().getListEntities(result);
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
        boolean retorno = false;
        try {
            entity = generateIds(entity, Mode.PERSIST);
            getQueryBuilder().insert(entity);
            retorno = true;
        } catch (OlivaDevelopException e) {
            Log.e("ERROR", e.getMessage());
        }
       /* JSONObject result = ServiceDAO.getInstance().newCall(ServiceURL.CREATE, getQueryBuilder().insert(entity));
        this.queryBuilder.getJsonPersistence().getEntity(result);*/
        return retorno;
    }

    @Override
    public boolean merge(T entity) throws ExecutionException, InterruptedException, JSONException {
        boolean retorno = false;
        getQueryBuilder().update(entity);
        retorno = true;
        return retorno;
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

    private T generateIds(T entity, Mode mode) throws InterruptedException, ExecutionException, JSONException, OlivaDevelopException {
        try {
            if ((!entity.isPersisted() && Mode.PERSIST.equals(mode)) || Mode.MERGE.equals(mode)) {
                for (Field f : Utils.getAllFieldsFromEntity(entity)) {
                    f.setAccessible(true);
                    Id pk = f.getAnnotation(Id.class);
                    RelatedEntity relatedEntity = f.getAnnotation(RelatedEntity.class);
                    if (Utils.isNotNull(pk)) {
                        // generamos la secuencia de la entidad.
                        Integer newId = this._sequenceController.getNextval(entity);
                        if (Utils.isNotNull(newId)) {
                            f.set(entity, newId);
                        } else {
                            throw new OlivaDevelopException(OlivaDevelopException.TypeException.PERSISTENCE, "ID is null");
                        }
                    } else if (Utils.isNotNull(relatedEntity)) {
                        // volvemos a ejecutar la fucnión con la entidad relacionada
                        if (!(f.get(entity) instanceof List)) {
                            generateIds((T) f.get(entity), mode);
                        }
                    }
                    f.setAccessible(false);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public enum Mode {
        PERSIST, MERGE, DELETE
    }
}
