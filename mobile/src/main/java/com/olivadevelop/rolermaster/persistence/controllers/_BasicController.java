package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.managers._RestService;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePair;
import com.olivadevelop.rolermaster.tools.utils.QueryBuilder;
import com.olivadevelop.rolermaster.tools.utils.intefraces.Entity;
import com.olivadevelop.rolermaster.tools.utils.intefraces._PersistenceMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */
/*values.add(new KeyValuePair("userId", String.valueOf(Preferences.getPrefs().getInt(Preferences.EnumBundle.SESSION_ID_USER, 0))));*/
public class _BasicController<T> implements _PersistenceMethods<T> {

    private _RestService service;
    private Class<T> entity;
    private QueryBuilder<T> queryBuilder;

    _BasicController(Class<T> entity) {
        this.entity = entity;
        this.queryBuilder = new QueryBuilder<>(entity);
    }

    public T find(Integer idEntity) throws ExecutionException, InterruptedException, JSONException {
        List<KeyValuePair> values = new ArrayList<>();
        values.add(new KeyValuePair("idEntity", String.valueOf(idEntity)));

        service = new _RestService("find_entity.php");
        service.execute(getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values));
        return parseJsonToEntity(service.get(), entity);
    }

    @Override
    public T find(FormBody query) throws ExecutionException, InterruptedException, JSONException {
        T retorno = null;
        if (Tools.isNotNull(query)) {
            service = new _RestService("find_entity.php");
            service.execute(query);
            retorno = parseJsonToEntity(service.get(), entity);
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
            retorno = parseJsonToListEntity(service.get(), entity);
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


    /**
     * Transforma el resultado JSON a la entidad correspondiente.
     *
     * @param json   result from service
     * @param entity entity class to parse it
     * @return entity object
     */
    private T parseJsonToEntity(JSONObject json, Class<T> entity) throws JSONException {
        T retorno = null;
        if (Tools.isNotNull(json)) {
            JSONArray array = json.getJSONArray(entity.getSimpleName());
            retorno = constructObject(array, entity, 0);
        }
        return retorno;
    }

    private List<T> parseJsonToListEntity(JSONObject json, Class<T> entity) throws JSONException {
        List<T> retorno = new ArrayList<>();
        if (Tools.isNotNull(json)) {
            JSONArray array = json.getJSONArray(entity.getSimpleName());
            if (Tools.isNotNull(array)) {
                for (int x = 0; x < array.length(); x++) {
                    retorno.add(constructObject(array, entity, x));
                }
            }
        }
        return retorno;
    }

    private T constructObject(JSONArray array, Class<T> entity, int pos) {
        T retorno = null;
        if (Tools.isNotNull(array)) {
            try {
                retorno = entity.getConstructor(JSONObject.class).newInstance(array.getJSONObject(pos));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }

    Class<T> getEntity() {
        return entity;
    }

    QueryBuilder<T> getQueryBuilder() {
        return queryBuilder;
    }
}
