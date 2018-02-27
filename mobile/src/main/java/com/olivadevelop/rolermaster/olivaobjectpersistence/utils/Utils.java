package com.olivadevelop.rolermaster.olivaobjectpersistence.utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.Entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 22/02/2018.
 */

public class Utils {
    public enum Error {
        ERROR_404(404), ERROR_500(500), ERROR_300(300), ERROR_400(400);
        private int val;

        Error(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    public static boolean isNull(Object object) {
        return object == null || (object instanceof String && object.toString().trim().equals(""));
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNull(TextView view) {
        return view == null || view.getText() == null || view.getText().toString().equals("");
    }

    public static boolean isNotNull(TextView view) {
        return !isNull(view);
    }

    public static boolean isNotNull(EditText view) {
        return !isNull(view) && isNotNull(view.getText()) && isNotNull(view.getText().toString().trim());
    }

    public static boolean isEmailValid(EditText email) {
        return isNotNull(email) && isEmailValid(email.getText().toString().trim());
    }

    public static boolean isEmailValid(String... emails) {
        boolean retorno = false;
        for (String email : emails) {
            if (!TextUtils.isEmpty(email.trim()) && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public static List<Field> getAllFieldsFromEntity(Entity entity) {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(entity.getClass().getDeclaredFields()));
        Class<?> e = entity.getClass().getSuperclass();
        boolean next = true;
        do {
            com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity ent = e.getAnnotation(com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity.class);
            if (isNotNull(ent)) {
                fields.addAll(Arrays.asList(e.getDeclaredFields()));
                e = e.getSuperclass();
            } else {
                next = false;
            }
        } while (next);
        return fields;
    }

    public static KeyValuePair<String, Object> getPkFromEntity(Entity entity) throws IllegalAccessException {
        KeyValuePair<String, Object> retorno = null;
        for (Field field : getAllFieldsFromEntity(entity)) {
            Id pk = field.getAnnotation(Id.class);
            if (Utils.isNotNull(pk)) {
                retorno = new KeyValuePair<>(field.getName(), field.get(entity));
            }
        }
        return retorno;
    }

    /**
     * Devuelve true si el nombre o el valor de la propiedad coincide con los que queremos omitir
     *
     * @param field
     * @param entity
     * @return
     * @throws IllegalAccessException
     */
    public static boolean ignoreField(Field field, _BasicEntity entity) throws IllegalAccessException {
        boolean retorno = false;
        List<Boolean> list = new ArrayList<>();
        list.add(_BasicEntity.CHANGE_FIELD.equals(field.getName()));
        list.add(_BasicEntity.CHANGE_FIELD.equals(field.get(entity)));
        list.add(_BasicEntity.SERIAL_VERSION_UID.equals(field.getName()));
        list.add(_BasicEntity.SERIAL_VERSION_UID.equals(field.get(entity)));
        list.add(_BasicEntity.ENTITY.equals(field.getName()));
        list.add(_BasicEntity.ENTITY.equals(field.get(entity)));
        list.add(_BasicEntity.PERSISTED.equals(field.get(entity)));
        for (Boolean bool : list) {
            if (bool) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }
}
