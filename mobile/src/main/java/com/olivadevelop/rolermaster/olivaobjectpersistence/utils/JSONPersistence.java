package com.olivadevelop.rolermaster.olivaobjectpersistence.utils;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.ManyToMany;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.ManyToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToMany;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.RolerMasterException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static com.olivadevelop.rolermaster.tools.utils.RolerMasterException.TypeException.PERSISTENCE;
import static com.olivadevelop.rolermaster.tools.utils.RolerMasterException.TypeException.RELATIONSHIP;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/02/2018.
 */

public class JSONPersistence<T extends _BasicEntity> {
    private static final String ENTITY = "entity";

    private Class<T> entityClass;

    public JSONPersistence(Class<T> entity) {
        this.entityClass = entity;
    }

    /**
     * Persistimos o actualizamos una sola entidad, sin importar las entidades relacionadas, solo guardaremos sus respectivos ID para relacionarlos
     *
     * @param entity
     * @return
     * @throws RolerMasterException
     */
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

    /**
     * complementa el método persistenceJSONObject para mejor visibilidad. Transforma la entidad a JSON
     *
     * @param entity
     * @return
     * @throws IllegalAccessException
     * @throws JSONException
     * @throws RolerMasterException
     */
    private JSONObject transformToJSON(T entity) throws IllegalAccessException, JSONException, RolerMasterException {
        JSONObject retorno = new JSONObject();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fName = field.getName();
            if (_BasicEntity.CHANGE_FIELD.equals(fName) || _BasicEntity.SERIAL_VERSION_UID.equals(fName)) {
                // obviamos esas dos columnas
                continue;
            }
            Persistence persistenceField = field.getAnnotation(Persistence.class);
            if (Tools.isNotNull(persistenceField)) {
                if (Tools.isNotNull(persistenceField.unique())) {
                    if (Tools.isNull(field.get(entity))) {
                        // Si es único, no puede ser nulo, cancelamos la operación
                        throw new RolerMasterException(PERSISTENCE);
                    }
                }
                if (Tools.isNotNull(persistenceField.columnName())) {
                    fName = persistenceField.columnName();
                }
            }
            KeyValuePair<String, Object> fieldValue = getValueFromField(field, entity);
            if (fieldValue != null) {
                retorno.put(fieldValue.getKey(), fieldValue.getValue());
            } else {
                retorno.put(fName, "null");
            }
            field.setAccessible(false);
        }
        return retorno;
    }

    /**
     * Obtiene el valor de una propiedad de la entidad y, en caso de que sea una entidad relacionada enlazamos el id
     *
     * @param field
     * @param entityMaster
     * @return
     * @throws IllegalAccessException
     * @throws RolerMasterException
     */
    private KeyValuePair<String, Object> getValueFromField(Field field, T entityMaster) throws IllegalAccessException, RolerMasterException {
        KeyValuePair<String, Object> retorno = null;
        Object value = field.get(entityMaster);
        RelatedEntity relatedEntity = field.getAnnotation(RelatedEntity.class);
        if (Tools.isNotNull(relatedEntity)) {
            // Ahora debemos obtener el tipo de relación.
            OneToOne oneToOne = field.getAnnotation(OneToOne.class);
            OneToMany oneToMany = field.getAnnotation(OneToMany.class);
            ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
            ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);
            if (Tools.isNotNull(oneToOne)) {
                // Si es una relación uno a uno o uno a muchos, directamente podemos transformar en una Entity para obtener su identificador
                _BasicEntity entity = (_BasicEntity) value;
                retorno = new KeyValuePair<>();
                if (Tools.isNotNull(relatedEntity.joinColumn())) {
                    retorno.setKey(relatedEntity.joinColumn());
                } else {
                    retorno.setKey(field.getName());
                }
                for (Field fieldRelated : entity.getClass().getDeclaredFields()) {
                    fieldRelated.setAccessible(true);
                    if (_BasicEntity.CHANGE_FIELD.equals(fieldRelated.getName())
                            || _BasicEntity.SERIAL_VERSION_UID.equals(fieldRelated.getName())) {
                        // obviamos esas dos columnas
                        continue;
                    }
                    Id pk = fieldRelated.getAnnotation(Id.class);
                    if (Tools.isNotNull(pk)) {
                        retorno.setValue(fieldRelated.get(entity));
                        fieldRelated.setAccessible(false);
                        break;
                    }
                    fieldRelated.setAccessible(false);
                }
           /* } else if (Tools.isNotNull(oneToMany)) {
                // no hacemos nada, es decir, se omite la relación puesto que es la entidad relacionada quien tendrá el identificador del padre
            } else if (Tools.isNotNull(manyToOne)) {
                // no hacemos nada, es decir, se omite la relación puesto que es la entidad relacionada (padre) quien tendrá el identificador del hijo
            } else if (Tools.isNotNull(manyToMany)) {
                // Si es una relación muchos a uno o muchos a muchos, transformarmamos en una List<Entity> para asignar el identificador del padre a cada entidad.
                List<Entity> lista = (List<Entity>) value;
                for (Entity ent : lista) {
                    _BasicEntity entity = (_BasicEntity) ent;
                }*/
            } else {
                throw new RolerMasterException(RELATIONSHIP);
            }
        }
        return retorno;
    }

    public List<T> convert(JSONArray array) throws JSONException {
        List<T> retorno = new ArrayList<>();
        if (Tools.isNotNull(array)) {
            for (int x = 0; x < array.length(); x++) {
                Object value = array.get(x);
                if (value instanceof JSONObject) {
                    retorno = parseJsonToListEntity((JSONObject) value, this.entityClass);
                } else {
                    retorno.add((T) value);
                }
            }
        }
        return retorno;
    }

    public T getNewEntity(JSONObject json) throws JSONException {
        return parseJsonToEntity(json, this.entityClass);
    }

    /**
     * Transforma un JSON a una lista de entidades
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public List<T> getNewListEntities(JSONObject json) throws JSONException {
        return parseJsonToListEntity(json, this.entityClass);
    }

    public String getPersistenceFieldName(String fieldName) {
        String retorno = fieldName;
        try {
            if (Tools.isNotNull(this.entityClass)) {
                Field field = this.entityClass.getDeclaredField(fieldName);
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
}
