package com.olivadevelop.rolermaster.tools;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.olivadevelop.rolermaster.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 29/12/2017.
 * RolerMaster
 */

public abstract class Tools {

    public static int TIME_TO_EXIT = 2000; // Miliseconds

    public static void newBooleanDialog(Context c, @StringRes int idTitle, @StringRes int idMessage, final Callable<Void> funcPos) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(c);
        alert.setTitle(c.getString(idTitle));
        alert.setMessage(c.getString(idMessage));
        alert.setCancelable(false);
        alert.setPositiveButton(c.getString(R.string.true_dialog), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    funcPos.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        alert.setNegativeButton(c.getString(R.string.false_dialog), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public static void newInfoDialog(Context c, @StringRes int idTitle, @StringRes int idMessage, final Callable<Void> funcPos) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(c);
        alert.setTitle(c.getString(idTitle));
        alert.setMessage(c.getString(idMessage));
        alert.setCancelable(false);
        alert.setPositiveButton(c.getString(R.string.true_dialog), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    funcPos.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        alert.show();
    }

    public static void Logger(Context c, String text) {
        Toast.makeText(c, text, Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), text);
    }

    public static void Logger(Context c, @StringRes int idText) {
        Toast.makeText(c, c.getString(idText), Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), c.getString(idText));
    }

    public static void Logger(Context c, Exception e) {
        Toast.makeText(c, e.getMessage(), Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), e.getLocalizedMessage());
        e.printStackTrace();
    }

    public static void Logger(Fragment c, String text) {
        Toast.makeText(c.getContext(), text, Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), text);
    }

    public static void Logger(Fragment c, @StringRes int idText) {
        Toast.makeText(c.getContext(), c.getContext().getString(idText), Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), c.getString(idText));
    }

    public static void Logger(Fragment c, Exception e) {
        Toast.makeText(c.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), e.getLocalizedMessage());
        e.printStackTrace();
    }

    public static boolean isNull(Object object) {
        return object == null || object.toString().trim().equals("");
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


    public static void createSpinner(Activity a, View view, @IdRes int idSpinner, List<KeyValuePair> values) {
        Spinner spinner = view.findViewById(idSpinner);
        spinner.setAdapter(new SpinnerAdapter(a, R.layout.custom_spinner, values)); //if you r using fragment
    }

    public static List<KeyValuePair> objToKeyValuePair(List<?> listEntities, String propertyKey, String propertyValue) throws NoSuchFieldException, IllegalAccessException {
        List<KeyValuePair> retorno = new ArrayList<>();
        Object key = null;
        Object val = null;
        if (isNotNull(listEntities)) {
            for (Object entity : listEntities) {
                if (isNotNull(propertyKey)) {
                    Field fkey = entity.getClass().getDeclaredField(propertyKey);
                    fkey.setAccessible(true);
                    key = fkey.get(entity);
                }
                if (isNotNull(propertyKey)) {
                    Field fVal = entity.getClass().getDeclaredField(propertyValue);
                    fVal.setAccessible(true);
                    val = fVal.get(entity);
                }
                retorno.add(new KeyValuePair(key, val.toString()));
            }
        }
        return retorno;
    }

}
