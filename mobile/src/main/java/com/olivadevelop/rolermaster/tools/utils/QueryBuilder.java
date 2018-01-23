package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.intefraces.Persistence;

import java.lang.reflect.Field;
import java.util.List;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 23/01/2018.
 */

public class QueryBuilder<T> {

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

    public QueryBuilder(Class<T> entity) {
        this.entity = entity;
    }

    private static final String TYPE_QUERY = "typeQuery";
    private static final String ENTITY_QUERY = "entityQuery";

    public FormBody createQuery(TypeQuery typeQuery, List<KeyValuePair> values) {
        FormBody.Builder query = new FormBody.Builder();
        query.add(ENTITY_QUERY, entity.getSimpleName());
        query.add(TYPE_QUERY, String.valueOf(typeQuery.getVal()));
        if (Tools.isNotNull(values)) {
            for (KeyValuePair obj : values) {
                query.add(getPersistenceFieldName(String.valueOf(obj.getKey())), obj.getLabel());
            }
        }
        return query.build();
    }

    private String getPersistenceFieldName(String fieldName) {
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
