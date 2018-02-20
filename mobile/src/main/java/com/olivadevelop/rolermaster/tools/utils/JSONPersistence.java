package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Id;
import com.olivadevelop.rolermaster.persistence.entities.annotations.ManyToMany;
import com.olivadevelop.rolermaster.persistence.entities.annotations.ManyToOne;
import com.olivadevelop.rolermaster.persistence.entities.annotations.OneToMany;
import com.olivadevelop.rolermaster.persistence.entities.annotations.OneToOne;
import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.persistence.entities.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Entity;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;

import static com.olivadevelop.rolermaster.tools.utils.RolerMasterException.TypeException.PERSISTENCE;
import static com.olivadevelop.rolermaster.tools.utils.RolerMasterException.TypeException.RELATIONSHIP;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/02/2018.
 */

public class JSONPersistence<T extends BasicEntity> {
    private static final String ENTITY = "entity";

    /**
     * Persistimos una sola entidad, sin importar las entidades relacionadas, solo guardaremos sus respectivos ID para relacionarlos
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

    private KeyValuePair<String, Object> getValueFromField(Field field, T entityMaster) throws IllegalAccessException, RolerMasterException {
       /* Object fieldValue = field.get(this);
        if (fieldValue instanceof BasicEntity) {
            BasicEntity entity = (BasicEntity) fieldValue;
            if (!fullObject) {
                RelatedEntity relatedEntity = field.getAnnotation(RelatedEntity.class);
                if (Tools.isNotNull(relatedEntity)) {
                    if (Tools.isNotNull(relatedEntity.joinColumn())) {
                        fName = relatedEntity.joinColumn();
                    }
                    Field[] fieldsRelatedEntity = entity.getClass().getDeclaredFields();
                                *//*if (Tools.isNotNull(fieldsRelatedEntity)) {*//*
                    for (Field fieldRelated : fieldsRelatedEntity) {
                        fieldRelated.setAccessible(true);
                        if (MapEntities.CHANGE_FIELD.equals(fieldRelated.getName()) || MapEntities.SERIAL_VERSION_UID.equals(fieldRelated.getName())) {
                            // obviamos esas dos columnas
                            continue;
                        }
                        Id pk = fieldRelated.getAnnotation(Id.class);
                        if (Tools.isNotNull(pk)) {
                            fieldValue = fieldRelated.get(entity);
                        }
                        fieldRelated.setAccessible(false);
                    }
                               *//* } else {
                                    // Es una clase extendida, por lo intentaremos acceder a su clase padre, acceder a sus propiedades y generar la entidad con el nombre de la clase hija
                                    Log.e("SUPERCLASS", "" + entity.getClass().getSuperclass());
                                }*//*
                } else {
                    // si fullobject es true, ponemos to-do el objeto en el json, incluyendo las entidades relacionadas
                    //retorno.append(((BasicEntity) value).toString(false));
                    fieldValue = entity.toJSONPersistence();
                }
            }
        }*/
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
                BasicEntity entity = (BasicEntity) value;
                retorno = new KeyValuePair<>();
                if (Tools.isNotNull(relatedEntity.joinColumn())) {
                    retorno.setKey(relatedEntity.joinColumn());
                } else {
                    retorno.setKey(field.getName());
                }
                for (Field fieldRelated : entity.getClass().getDeclaredFields()) {
                    fieldRelated.setAccessible(true);
                    if (MapEntities.CHANGE_FIELD.equals(fieldRelated.getName()) || MapEntities.SERIAL_VERSION_UID.equals(fieldRelated.getName())) {
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
                    BasicEntity entity = (BasicEntity) ent;
                }*/
            } else {
                throw new RolerMasterException(RELATIONSHIP);
            }
        }
        return retorno;
    }
}
