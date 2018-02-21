package com.olivadevelop.rolermaster.olivaobjectpersistence.utils;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 23/01/2018.
 */

public class QueryBuilder<T extends _BasicEntity> {

    public enum TypeQuery {
        FIND_ALL(0), FIND_ONE(1);

        private int val;

        TypeQuery(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    private Class<T> entity;
    private ConverterJSONArrayToList<T> converter;
    private JSONPersistence<T> jsonPersistence;

    public QueryBuilder(Class<T> entity) {
        this.entity = entity;
        this.converter = new ConverterJSONArrayToList<>(entity);
        this.jsonPersistence =new JSONPersistence<>(entity);
    }

    private static final String TYPE_QUERY = "typeQuery";
    private static final String ENTITY_QUERY = "entityQuery";

    public FormBody createQuery(TypeQuery typeQuery, List<KeyValuePair<String, ?>> values) {
        FormBody.Builder query = new FormBody.Builder();
        query.add(ENTITY_QUERY, entity.getSimpleName());
        query.add(TYPE_QUERY, String.valueOf(typeQuery.getVal()));
        if (Tools.isNotNull(values)) {
            for (KeyValuePair obj : values) {
                query.add(converter.getPersistenceFieldName(String.valueOf(obj.getKey())), obj.getValueAsString());
            }
        }
        return query.build();
    }

    public FormBody insertQuery(List<KeyValuePair<String, ?>> entities) {
        FormBody.Builder query = new FormBody.Builder();
        if (Tools.isNotNull(entities)) {
            for (KeyValuePair obj : entities) {
                query.add(converter.getPersistenceFieldName(String.valueOf(obj.getKey())), obj.getValueAsString());
            }
        }
        return query.build();
    }

    public FormBody insertQuery(T entity) throws JSONException {
        FormBody.Builder query = new FormBody.Builder();
        try {
            List<_BasicEntity> retorno = new ArrayList<>();
            getJsonEntities(retorno, (_BasicEntity) entity);
            Collections.reverse(retorno);
            for (_BasicEntity bEnti : retorno) {
                query.add("entity[]", bEnti.toJSONPersistence().toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return query.build();
    }

    private void getJsonEntities(List<_BasicEntity> retorno, _BasicEntity entity) throws JSONException, IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldValue = field.get(entity);
            RelatedEntity relatedEntity = field.getAnnotation(RelatedEntity.class);
            if (Tools.isNotNull(relatedEntity)) {
                getJsonEntities(retorno, (_BasicEntity) fieldValue);
            }
            field.setAccessible(false);
        }
        retorno.add(entity);
    }
}
