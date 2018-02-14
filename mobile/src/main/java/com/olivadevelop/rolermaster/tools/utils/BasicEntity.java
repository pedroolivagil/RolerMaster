package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Id;
import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.persistence.entities.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Entity;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/01/2018.
 */

public abstract class BasicEntity implements Entity {

    private static final String QUOTES = "\"";
    private static final String ENTITY = "entity";

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
                            Object value = null;
                            try {
                                value = json.get(fName);
                            } catch (Exception e) {
                                continue;
                            }
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
        return toString(true);
    }

    public String toString(boolean fullObject) {
        return toJSON(fullObject).toString();
    }

    @Override
    public JSONObject toJSONPersistence() throws JSONException {
        return toJSON(false);
    }

    @Override
    public JSONObject toJSON(boolean fullObject) {
        JSONObject retorno = new JSONObject();
        /*StringBuilder retorno = new StringBuilder();*/
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
                JSONObject jsonEntity = new JSONObject();
                int count = 0;
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fName = field.getName();
                    if (MapEntities.CHANGE_FIELD.equals(fName) || MapEntities.SERIAL_VERSION_UID.equals(fName)) {
                        // obviamos esas dos columnas
                        count++;
                        continue;
                    }
                    Persistence persistenceField = field.getAnnotation(Persistence.class);
                    if (Tools.isNotNull(persistenceField) && Tools.isNotNull(persistenceField.columnName())) {
                        fName = persistenceField.columnName();
                    }

                    /*Object tempValue = field.get(this);*/
                    Object fieldValue = field.get(this);
                    if (fieldValue instanceof BasicEntity) {
                        if (fullObject) {
                            // Por cada entidad relacionada, obtenemos su ID a través del @ID de dicha
                            // clase.

                            // Usaremos el join column definido en la clase padre para almacenar la
                            // información.

                            // En caso de que no exista joincolumn, usaremos el nombre de la propiedad
                            // ID de la clase relacionada, en caso contrario dejaremos el nombre de la
                            // propiedad de la clase padre.
                            RelatedEntity relatedEntity = field.getAnnotation(RelatedEntity.class);
                            if (Tools.isNotNull(relatedEntity)) {
                                if (Tools.isNotNull(relatedEntity.joinColumn())) {
                                    fName = relatedEntity.joinColumn();
                                }
                                Integer id = null;
                            /*Class<?> relEntity = Class.forName(relatedEntity.to());
                            relEntity.getDeclaredFields()
                            retorno.append(fName);*/
                                BasicEntity entity = (BasicEntity) fieldValue;
                                for (Field fieldRelated : entity.getClass().getDeclaredFields()) {
                                    fieldRelated.setAccessible(true);
                                    Id pk = fieldRelated.getAnnotation(Id.class);
                                    if (Tools.isNotNull(pk)) {
                                        id = field.getInt(entity);
                                    }
                                    fieldRelated.setAccessible(false);
                                }
                                retorno.put(fName, id);
                            } else {
                                // si fullobject es true, ponemos to-do el objeto en el json, incluyendo las entidades relacionadas
                                //retorno.append(((BasicEntity) value).toString(false));
                                retorno.put(fName, fieldValue.toString());
                            }
                        }
                    }
                    jsonEntity.put(fName, fieldValue);
                    field.setAccessible(false);
                    count++;
                }
                retorno.put(className, jsonEntity);
            }
        } catch (IllegalAccessException | JSONException e) {
            e.printStackTrace();
        }
        return retorno;
    }
}
