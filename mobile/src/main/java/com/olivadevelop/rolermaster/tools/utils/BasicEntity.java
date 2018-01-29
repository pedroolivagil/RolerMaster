package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Entity;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/01/2018.
 */

public abstract class BasicEntity implements Entity {

    private static final String QUOTES = "\"";

    protected BasicEntity() {
    }

    public BasicEntity(JSONObject json) throws JSONException {
        toEntity(json);
    }

    @Override
    public String generateCode() {
        return null;
    }

    @Override
    public void toEntity(JSONObject json) throws JSONException {
        if (Tools.isNotNull(json)) {
            try {
                Field[] fields = getClass().getDeclaredFields();
                if (Tools.isNotNull(fields) && fields.length > 0) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        String fName = field.getName();
                        if (!MapEntities.CHANGE_FIELD.equals(fName) && !MapEntities.SERIAL_VERSION_UID.equals(fName)) {
                            Persistence persistence = field.getAnnotation(Persistence.class);
                            if (Tools.isNotNull(persistence) && Tools.isNotNull(persistence.columnName())) {
                                fName = persistence.columnName();
                            }
                            Object value = json.get(fName);

                            if (value instanceof Boolean
                                    || value instanceof Byte
                                    || value instanceof Integer
                                    || value instanceof Long
                                    || value instanceof Float
                                    || value instanceof Double
                                    || value instanceof String) {
                                field.set(this, value);
                            /*} else if (value instanceof byte[]) {
                                field.set(this, ImagePicasso.StringTobase64(value);*/
                            } else if (value instanceof JSONArray) {
                                ConverterJSONArrayToList<Integer> converter = new ConverterJSONArrayToList<>(Integer.class);
                                field.set(this, converter.convert((JSONArray) value));
                            } else if (value instanceof JSONObject) {
                                Class entity = MapEntities.findByString(fName);
                                if (Tools.isNotNull(entity)) {
                                    Object newEntity = entity.getConstructor(JSONObject.class).newInstance(value);
                                    if (Tools.isNotNull(newEntity)) {
                                        field.set(this, newEntity);
                                    }
                                }
                            }
                        }
                        field.setAccessible(false);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        try {
            Field[] fields = getClass().getDeclaredFields();
            if (Tools.isNotNull(fields) && fields.length > 0) {
                Persistence persistenceClass = getClass().getAnnotation(Persistence.class);
                String className = getClass().getSimpleName();
                if (Tools.isNotNull(persistenceClass) && Tools.isNotNull(persistenceClass.collectionName())) {
                    className = persistenceClass.collectionName();
                }
                retorno.append("{");
                retorno.append(QUOTES).append(className).append(QUOTES).append(":{");
                int count = 0;
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fName = field.getName();
                    if (MapEntities.CHANGE_FIELD.equals(fName) || MapEntities.SERIAL_VERSION_UID.equals(fName)) {
                        count++;
                        continue;
                    }
                    Persistence persistenceField = field.getAnnotation(Persistence.class);
                    if (Tools.isNotNull(persistenceField) && Tools.isNotNull(persistenceField.columnName())) {
                        fName = persistenceField.columnName();
                    }
                    retorno.append(QUOTES).append(fName).append(QUOTES).append(":");

                    Object value = field.get(this);
                    if (value instanceof String) {
                        retorno.append(QUOTES);
                        retorno.append(value);
                        retorno.append(QUOTES);
                    } else if (value instanceof Boolean
                            || value instanceof Byte
                            || value instanceof Integer
                            || value instanceof Long
                            || value instanceof Float
                            || value instanceof Double) {
                        retorno.append(value);
                    } else if (value instanceof List) {
                        retorno.append(value);
                    } else if (value instanceof byte[]) {
                        retorno.append(ImagePicasso.base64ToString((byte[]) value));
                    } else if (value instanceof BasicEntity) {
                        retorno.append(value.toString());
                    } else {
                        retorno.append(String.valueOf(value));
                    }
                    if (count < (fields.length - 3)) {
                        retorno.append(",");
                    }
                    field.setAccessible(false);
                    count++;
                }
                retorno.append("}");
                retorno.append("}");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return retorno.toString();
    }
}
