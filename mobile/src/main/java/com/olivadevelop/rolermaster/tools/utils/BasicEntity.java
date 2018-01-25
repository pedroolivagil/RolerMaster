package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.intefraces.Entity;
import com.olivadevelop.rolermaster.tools.utils.intefraces.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/01/2018.
 */

public abstract class BasicEntity implements Entity {

    private static final String QUOTES = "\"";
    private static final String SERIAL_VERSION_UID = "serialVersionUID";
    private static final String CHANGE_FIELD = "$change";

    public static final String COMMON_FIELD_TRANS = "translation";

    protected BasicEntity() {
    }

    public BasicEntity(JSONObject json) throws JSONException {
        construct(json);
    }

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        try {
            Field[] fields = getClass().getDeclaredFields();
            if (Tools.isNotNull(fields) && fields.length > 0) {
                retorno.append("{");
                retorno.append(QUOTES).append(getClass().getSimpleName()).append(QUOTES).append(":{");
                int count = 0;
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fName = field.getName();
                    if (CHANGE_FIELD.equals(fName) || SERIAL_VERSION_UID.equals(fName)) {
                        count++;
                        continue;
                    }
                    Persistence persistence = field.getAnnotation(Persistence.class);
                    if (Tools.isNotNull(persistence) && Tools.isNotNull(persistence.columnName())) {
                        fName = persistence.columnName();
                    }
                    retorno.append(QUOTES).append(fName).append(QUOTES).append(":");

                    Object value = field.get(this);
                    if (value instanceof String) {
                        retorno.append(QUOTES);
                        retorno.append(value);
                        retorno.append(QUOTES);
                    } else if (value instanceof Boolean
                            || value instanceof Integer
                            || value instanceof Long
                            || value instanceof Float
                            || value instanceof Double) {
                        retorno.append(value);
                    } else if (value instanceof List) {
                        retorno.append(value);
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
