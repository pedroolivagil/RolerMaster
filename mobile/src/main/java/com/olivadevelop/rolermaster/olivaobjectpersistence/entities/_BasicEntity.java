package com.olivadevelop.rolermaster.olivaobjectpersistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToMany;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Unique;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Utils;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/01/2018.
 */
@com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity
public abstract class _BasicEntity implements Entity {

    public static final String ENTITY = "entity";
    public static final String SERIAL_VERSION_UID = "serialVersionUID";
    public static final String CHANGE_FIELD = "$change";
    public static final String PERSISTED = "_persisted";

    private boolean _persisted = false;

    protected _BasicEntity() {
    }

    public _BasicEntity(JSONObject json) throws JSONException {
        toEntity(json);
    }

    public boolean isPersisted() {
        return _persisted;
    }

    @Override
    public String generateCode() {
        return null;
    }

    /**
     * Convertimos el JSON a Entity.
     *
     * @param json
     * @throws JSONException
     */
    @Override
    public void toEntity(JSONObject json) throws JSONException {
        // Se transformará cada valor a su tipo y se asignará a su propiedad correspondiente.
        // Cada KEY del JSONObject principal, corresponderá con el nombre de las columna de la BBDD
        // por lo que habrá que buscar la propiedad que coincida por nombre o por JoinColumn de la
        // RelatedEntity. Si la relación es una OneToMany, una lista, se debe crear un objeto, a
        // través del constructor JSONObject, por cada elemento de la lista e insertarlo a dicha
        // lista. Si es una relación oneToOne, se carga el objeto directamente a través del
        // constructor JSONObject de cada Entity.

        // El proceso debe ser completo, es decir, que debe cargar todas las relaciones, no solo un
        // nivel, ejemplo:
        // Objeto 1 -> relación con Objeto 2 -> relación con Objeto 3
        // El Objeto 1 no tiene por que tener relación con el Objeto 3, pero este depende del Objeto 2.
        if (Tools.isNotNull(json)) {
            try {
                // recuperamos las propiedades
                //Field[] fields = getClass().getDeclaredFields();
                List<Field> fields = Utils.getAllFieldsFromEntity(this);
                if (Tools.isNotNull(fields)) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        String fName = field.getName();
                        if (!Utils.ignoreField(field, this)) {
                            // Obtenemos el nombre de la propiedad que se le pasa por el JSON,
                            // por defecto será el nombre de la propiedad Java (el nombre en una
                            // lista será el nombre de la clase relacionada en plural)
                            Persistence persistence = field.getAnnotation(Persistence.class);
                            if (Tools.isNotNull(persistence) && Tools.isNotNull(persistence.columnName())) {
                                fName = persistence.columnName();
                            }
                            // Obtenemos el valor del JSON, que puede ser de cualquier tipo
                            Object value = json.get(fName);

                            // Si el valor es alguno de los primitivos, es decir, no es una lista ni una entidad
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
                                // Si es un array, debemos generar un objeto por cada elemento del array y asignarlo a la lista
                                JSONArray jsonArray = (JSONArray) value;
                                OneToMany oneToMany = field.getAnnotation(OneToMany.class);
                                List<_BasicEntity> entities = new ArrayList<>();
                                for (int x = 0; x < jsonArray.length(); x++) {
                                    JSONObject jObj = jsonArray.getJSONObject(x);
                                    _BasicEntity elem = (_BasicEntity) oneToMany.mappingClass().getConstructor(JSONObject.class).newInstance(jObj);
                                    entities.add(elem);
                                }
                                field.set(this, entities);
                            } else if (value instanceof JSONObject) {
                                OneToOne oneToOne = field.getAnnotation(OneToOne.class);
                                _BasicEntity elem = (_BasicEntity) oneToOne.mappingClass().getConstructor(JSONObject.class).newInstance(value);
                                field.set(this, elem);
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
    public boolean equals(Object obj) {
        boolean retorno = true;
        try {
            _BasicEntity entity = (_BasicEntity) obj;
            List<Object> valuesObj = new ArrayList<>();
            List<Object> valuesThis = new ArrayList<>();
            for (Field fieldRelated : Tools.getAllFieldsFromEntity(entity)) {
                fieldRelated.setAccessible(true);
                Id pk = fieldRelated.getAnnotation(Id.class);
                Unique unique = fieldRelated.getAnnotation(Unique.class);
                if (Tools.isNotNull(pk) || Tools.isNotNull(unique)) {
                    valuesObj.add(fieldRelated.get(obj));
                }
                fieldRelated.setAccessible(false);
            }
            for (Field fieldRelated : Tools.getAllFieldsFromEntity(this)) {
                fieldRelated.setAccessible(true);
                Id pk = fieldRelated.getAnnotation(Id.class);
                Unique unique = fieldRelated.getAnnotation(Unique.class);
                if (Tools.isNotNull(pk) || Tools.isNotNull(unique)) {
                    valuesThis.add(fieldRelated.get(this));
                }
                fieldRelated.setAccessible(false);
            }

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

    public JSONObject toJSON() {
        JSONObject retorno = new JSONObject();
        try {
            List<Field> fields = Utils.getAllFieldsFromEntity(this);
            for (Field field : fields) {
                field.setAccessible(true);
                if (!Utils.ignoreField(field, this)) {
                    RelatedEntity relatedEntity = field.getAnnotation(RelatedEntity.class);
                    if (Utils.isNotNull(relatedEntity)) {
                        OneToOne oneToOne = field.getAnnotation(OneToOne.class);
                        OneToMany oneToMany = field.getAnnotation(OneToMany.class);
                        if (Utils.isNotNull(oneToOne)) {
                            retorno.put(field.getName(), ((_BasicEntity) field.get(this)).toJSON());
                        } else if (Utils.isNotNull(oneToMany)) {
                            JSONArray array = new JSONArray();
                            List<_BasicEntity> list = (List<_BasicEntity>) field.get(this);
                            if (Utils.isNotNull(list)) {
                                for (_BasicEntity b : list) {
                                    array.put(b.toJSON());
                                }
                            }
                            retorno.put(field.getName(), array);
                        }
                    } else {
                        // Si no hay anotación de relación, es un valor primitivo
                        retorno.put(field.getName(), field.get(this));
                    }
                }
                field.setAccessible(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }
}
