package com.olivadevelop.rolermaster.olivaobjectpersistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Unique;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.Entity;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/01/2018.
 */

public abstract class _BasicEntity implements Entity {

    private static final String ENTITY = "entity";
    public static final String SERIAL_VERSION_UID = "serialVersionUID";
    public static final String CHANGE_FIELD = "$change";

    protected _BasicEntity() {
    }

    public _BasicEntity(JSONObject json) throws JSONException {
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
                        if (!CHANGE_FIELD.equals(fName) && !SERIAL_VERSION_UID.equals(fName)) {
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
                            // TODO: los byte[] agregados a la bbdd, se almacenan como strings, por el momento. Valorar si es correcto o buscar una forma más adecuada para hacer el cast como una anotación o algo
                                field.set(this, ImagePicasso.StringTobase64(value);*/
                            } else if (value instanceof JSONArray) {
                                // TODO: Revisar porque ya no se almacena una lista de id, sino de objetos, por lo que rara (o ninguna) vez entrará aquí para cargar una entidad
                               /* ConverterJSONArrayToList<Integer> converter = new ConverterJSONArrayToList<>(Integer.class);
                                JSONPersistence<Integer> converter = new
                                field.set(this, converter.convert((JSONArray) value));*/
                            } else if (value instanceof JSONObject) {
                                // TODO: Revisar para completar la carga del objeto mediante la entidad relacionada
                                /*Class entity = MapEntities.findByString(fName);
                                if (Tools.isNotNull(entity)) {
                                    Object newEntity = entity.getConstructor(JSONObject.class).newInstance(value);
                                    if (Tools.isNotNull(newEntity)) {
                                        field.set(this, newEntity);
                                    }
                                }*/
                            }
                        }
                        field.setAccessible(false);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            /*} catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();*/
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
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fName = field.getName();
                    if (CHANGE_FIELD.equals(fName) || SERIAL_VERSION_UID.equals(fName)) {
                        // obviamos esas dos columnas
                        continue;
                    }
                    Persistence persistenceField = field.getAnnotation(Persistence.class);
                    if (Tools.isNotNull(persistenceField) && Tools.isNotNull(persistenceField.columnName())) {
                        fName = persistenceField.columnName();
                    }

                    Object fieldValue = field.get(this);
                    if (fieldValue instanceof _BasicEntity) {
                        _BasicEntity entity = (_BasicEntity) fieldValue;
                        if (!fullObject) {
                            RelatedEntity relatedEntity = field.getAnnotation(RelatedEntity.class);
                            if (Tools.isNotNull(relatedEntity)) {
                                if (Tools.isNotNull(relatedEntity.joinColumn())) {
                                    fName = relatedEntity.joinColumn();
                                }
                                Field[] fieldsRelatedEntity = entity.getClass().getDeclaredFields();
                                /*if (Tools.isNotNull(fieldsRelatedEntity)) {*/
                                for (Field fieldRelated : fieldsRelatedEntity) {
                                    fieldRelated.setAccessible(true);
                                    if (CHANGE_FIELD.equals(fieldRelated.getName()) || SERIAL_VERSION_UID.equals(fieldRelated.getName())) {
                                        // obviamos esas dos columnas
                                        continue;
                                    }
                                    Id pk = fieldRelated.getAnnotation(Id.class);
                                    if (Tools.isNotNull(pk)) {
                                        fieldValue = fieldRelated.get(entity);
                                    }
                                    fieldRelated.setAccessible(false);
                                }
                               /* } else {
                                    // Es una clase extendida, por lo intentaremos acceder a su clase padre, acceder a sus propiedades y generar la entidad con el nombre de la clase hija
                                    Log.e("SUPERCLASS", "" + entity.getClass().getSuperclass());
                                }*/
                            } else {
                                // si fullobject es true, ponemos to-do el objeto en el json, incluyendo las entidades relacionadas
                                //retorno.append(((_BasicEntity) value).toString(false));
                                fieldValue = entity.toJSONPersistence();
                            }
                        }
                    }
                    if (fieldValue != null) {
                        jsonEntity.put(fName, fieldValue);
                    } else {
                        jsonEntity.put(fName, "null");
                    }
                    field.setAccessible(false);
                }
                retorno.put(className, jsonEntity);
            }
        } catch (IllegalAccessException | JSONException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public boolean equals(Object obj) {
        boolean retorno = true;
        try {
            _BasicEntity entity = (_BasicEntity) obj;
            List<Object> valuesObj = new ArrayList<>();
            List<Object> valuesThis = new ArrayList<>();
            for (Field fieldRelated : entity.getClass().getDeclaredFields()) {
                fieldRelated.setAccessible(true);
                Id pk = fieldRelated.getAnnotation(Id.class);
                Unique unique = fieldRelated.getAnnotation(Unique.class);
                if (Tools.isNotNull(pk) || Tools.isNotNull(unique)) {
                    valuesObj.add(fieldRelated.get(obj));
                }
                fieldRelated.setAccessible(false);
            }
            for (Field fieldRelated : this.getClass().getDeclaredFields()) {
                fieldRelated.setAccessible(true);
                Id pk = fieldRelated.getAnnotation(Id.class);
                Unique unique = fieldRelated.getAnnotation(Unique.class);
                if (Tools.isNotNull(pk) || Tools.isNotNull(unique)) {
                    valuesThis.add(fieldRelated.get(this));
                }
                fieldRelated.setAccessible(false);
            }
            /*for (int x = 0; x < valuesObj.size(); x++) {
                if (!valuesObj.get(x).equals(valuesThis.get(x))) {
                    retorno = false;
                }
            }*/
            for (Object value1 : valuesThis) {
                for (Object value2 : valuesObj) {
                    if (value2.getClass().equals(value1.getClass()) && !value2.equals(value1)) {
                        retorno = false;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            retorno = super.equals(obj);
            e.printStackTrace();
        }
        return retorno;
    }
}
