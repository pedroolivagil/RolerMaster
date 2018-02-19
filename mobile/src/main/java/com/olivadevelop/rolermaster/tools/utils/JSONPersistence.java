package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/02/2018.
 */

public class JSONPersistence<T extends BasicEntity> {
    private static final String ENTITY = "entity";

    public JSONObject persistenceJSONObject(T entity) throws RolerMasterException {
        JSONObject retorno = new JSONObject();
        try {
            Field[] fields = getClass().getDeclaredFields();
            if (Tools.isNotNull(fields) && fields.length > 0) {
                Persistence persistenceClass = getClass().getAnnotation(Persistence.class);
                String className = getClass().getSimpleName();
                if (Tools.isNotNull(persistenceClass) && Tools.isNotNull(persistenceClass.collectionName())) {
                    className = persistenceClass.collectionName();
                }
                // Inicio de objeto JSON
                // le pasamos la entidad como parámetro de JSON
                retorno.put(ENTITY, className);
                retorno.put(className, transformToJSON(entity));
            }
        } catch (IllegalAccessException | JSONException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    private JSONObject transformToJSON(T entity) throws IllegalAccessException, JSONException, RolerMasterException {
        JSONObject retorno = new JSONObject();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fName = field.getName();
            if (MapEntities.CHANGE_FIELD.equals(fName) || MapEntities.SERIAL_VERSION_UID.equals(fName)) {
                // obviamos esas dos columnas
                continue;
            }
            Persistence persistenceField = field.getAnnotation(Persistence.class);
            if (Tools.isNotNull(persistenceField)) {
                if (Tools.isNotNull(persistenceField.unique())) {
                    if (Tools.isNull(field.get(entity))) {
                        // Si es único, no puede ser nulo, cancelamos la operación
                        throw new RolerMasterException(RolerMasterException.TypeException.PERSISTENCE);
                    }
                }
                if (Tools.isNotNull(persistenceField.columnName())) {
                    fName = persistenceField.columnName();
                }
            }
            Object fieldValue = getValueFromField(field, entity);
            if (fieldValue != null) {
                retorno.put(fName, fieldValue);
            } else {
                retorno.put(fName, "null");
            }
            field.setAccessible(false);
        }
        return retorno;
    }

    private Object getValueFromField(Field field, T entity) throws IllegalAccessException {
        Object value = field.get(entity);
        return null;
    }
}
