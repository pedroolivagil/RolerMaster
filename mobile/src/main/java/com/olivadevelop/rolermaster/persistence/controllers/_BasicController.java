package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.old.Entity;
import com.olivadevelop.rolermaster.persistence.managers._RestService;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Preferences;
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

public class _BasicController<T> implements _PersistenceMethods<T> {

    private static final String FIND_ALL = "0";
    private static final String FIND_ONE = "1";

    protected _RestService service;
    protected Class<T> entity;

    protected _BasicController(Class<T> entity) {
        this.entity = entity;
    }

    @Override
    public T find(Integer idEntity, _RestService.ActionService<T> actionService) throws ExecutionException, InterruptedException, JSONException {
        service = new _RestService("find_entity.php", actionService);
        service.execute(new FormBody.Builder()
                .add("typeQuery", FIND_ONE)
                .add("entity", entity.getSimpleName())
                .add("userId", String.valueOf(Preferences.getPrefs().getInt(Preferences.EnumBundle.SESSION_ID_USER, 0)))
                .add("idEntity", String.valueOf(idEntity))
                .build()
        );
        return parseJsonToEntity(service.get(), entity);
    }

    @Override
    public List<T> findAll(_RestService.ActionService<T> actionService) throws ExecutionException, InterruptedException, JSONException {
        service = new _RestService("find_entity.php", actionService);
        service.execute(new FormBody.Builder()
                .add("typeQuery", FIND_ALL)
                .add("entity", entity.getSimpleName())
                .add("userId", String.valueOf(Preferences.getPrefs().getInt(Preferences.EnumBundle.SESSION_ID_USER, 0)))
                .build()
        );
        return parseJsonToListEntity(service.get(), entity);
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
    protected T parseJsonToEntity(JSONObject json, Class<T> entity) throws JSONException {
        T retorno = null;
        if (Tools.isNotNull(json)) {
            JSONArray array = json.getJSONArray(entity.getSimpleName());
            retorno = constructObject(array, entity, 0);
        }
        return retorno;
    }

    protected List<T> parseJsonToListEntity(JSONObject json, Class<T> entity) throws JSONException {
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
}
