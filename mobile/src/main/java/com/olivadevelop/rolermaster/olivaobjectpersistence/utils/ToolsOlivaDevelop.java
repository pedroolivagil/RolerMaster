package com.olivadevelop.rolermaster.olivaobjectpersistence.utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;

import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.Entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 22/02/2018.
 */

public class ToolsOlivaDevelop {
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
        return getAllFieldsFromEntity(entity, false);
    }

    public static List<Field> getAllFieldsFromEntity(Entity entity, boolean withSuperClass) {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(entity.getClass().getDeclaredFields()));
        if (withSuperClass) {
            fields.addAll(Arrays.asList(entity.getClass().getSuperclass().getDeclaredFields()));
        }
        return fields;
    }
}
