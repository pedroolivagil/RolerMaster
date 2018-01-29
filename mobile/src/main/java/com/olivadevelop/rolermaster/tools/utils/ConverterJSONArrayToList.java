package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 */

public class ConverterJSONArrayToList<T> {

    private Class<T> entity;

    public ConverterJSONArrayToList(Class<T> entity) {
        this.entity = entity;
    }

    public List<T> convert(JSONArray array) throws JSONException {
        List<T> retorno = new ArrayList<>();
        if (Tools.isNotNull(array)) {
            for (int x = 0; x < array.length(); x++) {
                Object value = array.get(x);
                if (value instanceof JSONObject) {
                    retorno = parseJsonToListEntity((JSONObject) value, this.entity);
                } else {
                    retorno.add((T) value);
                }
            }
        }
        return retorno;
    }

    public T getNewEntity(JSONObject json) throws JSONException {
        return parseJsonToEntity(json, this.entity);
    }

    public List<T> getNewListEntities(JSONObject json) throws JSONException {
        return parseJsonToListEntity(json, this.entity);
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

    /**
     * Transforma un resultado jsonObject a una lista de entidades pasada por parámetro
     *
     * @param json
     * @param entity
     * @return
     * @throws JSONException
     */
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


    public String getPersistenceFieldName(String fieldName) {
        String retorno = fieldName;
        try {
            if (Tools.isNotNull(entity)) {
                Field field = entity.getDeclaredField(fieldName);
                Persistence persistence = field.getAnnotation(Persistence.class);
                if (Tools.isNotNull(persistence)) {
                    String fn = persistence.columnName();
                    if (Tools.isNotNull(fn)) {
                        retorno = fn;
                    } else {
                        if (Tools.isNotNull(field.getName())) {
                            retorno = field.getName();
                        }
                    }
                }
            }
        } catch (NoSuchFieldException e) {
        }
        return retorno;
    }
}
