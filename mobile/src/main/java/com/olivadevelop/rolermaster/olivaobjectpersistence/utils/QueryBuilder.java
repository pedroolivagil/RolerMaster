package com.olivadevelop.rolermaster.olivaobjectpersistence.utils;

import android.util.Log;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.*;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.util.*;

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
    private JSONPersistence<T> jsonPersistence;

    public QueryBuilder(Class<T> entity) {
        this.entity = entity;
        this.jsonPersistence = new JSONPersistence<>(entity);
    }

    private static final String TYPE_QUERY = "typeQuery";
    private static final String ENTITY_QUERY = "entityQuery";

    public FormBody createQuery(TypeQuery typeQuery, List<KeyValuePair<String, ?>> values) {
        FormBody.Builder query = new FormBody.Builder();
        query.add(ENTITY_QUERY, entity.getSimpleName());
        query.add(TYPE_QUERY, String.valueOf(typeQuery.getVal()));
        if (ToolsOlivaDevelop.isNotNull(values)) {
            for (KeyValuePair obj : values) {
                query.add(this.jsonPersistence.getPersistenceFieldName(String.valueOf(obj.getKey())), obj.getValueAsString());
            }
        }
        return query.build();
    }

    /**
     * Crea una query para enviar el objeto a la bbdd
     *
     * @param entity
     * @return
     * @throws JSONException
     */
    public FormBody insertQuery(T entity) throws JSONException {
        FormBody.Builder query = new FormBody.Builder();
        try {
            List<_BasicEntity> retorno = new ArrayList<>();
            createPersistenceList(retorno, entity);
            Collections.reverse(retorno);
            for (_BasicEntity bEnti : retorno) {
                query.add("entity[]", this.jsonPersistence.persistenceJSONObject((T) bEnti).toString());
                Log.e("entity[]", this.jsonPersistence.persistenceJSONObject((T) bEnti).toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (OlivaDevelopException e) {
            e.printStackTrace();
        }
        return query.build();
    }

    /**
     * Obtenemos las entidades relacionadas y las entidades relacionadas de las relacionadas. Cada
     * entidad relacionada aumentará el subnivel, siempre que sea una OneToMany, que será el orden
     * de inserción a la lista final de entidades.
     *
     * @param retorno
     * @param entity
     * @throws JSONException
     * @throws IllegalAccessException
     */
    private void createPersistenceList(List<_BasicEntity> retorno, _BasicEntity entity) throws JSONException, IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (_BasicEntity.CHANGE_FIELD.equals(field.getName()) || _BasicEntity.SERIAL_VERSION_UID.equals(field.getName())) {
                // obviamos esas dos columnas
                continue;
            }
            field.setAccessible(true);
            Object fieldValue = field.get(entity);
            RelatedEntity relatedEntity = field.getAnnotation(RelatedEntity.class);
            OneToOne oneToOne = field.getAnnotation(OneToOne.class);
            OneToMany oneToMany = field.getAnnotation(OneToMany.class);
            if (ToolsOlivaDevelop.isNotNull(relatedEntity)) {
                if (ToolsOlivaDevelop.isNotNull(oneToOne)) {
                    createPersistenceList(retorno, (_BasicEntity) fieldValue);
                } else if (ToolsOlivaDevelop.isNotNull(oneToMany)) {
                    /*for (_BasicEntity value : (List<_BasicEntity>) fieldValue) {
                        createPersistenceList(retorno, value);
                    }*/
                }
            }
            field.setAccessible(false);
        }
        retorno.add(entity);
    }

    public JSONPersistence<T> getJsonPersistence() {
        return jsonPersistence;
    }
}
