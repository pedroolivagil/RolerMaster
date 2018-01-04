package com.olivadevelop.rolermaster.tools;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.olivadevelop.rolermaster.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 29/12/2017.
 * RolerMaster
 */

public abstract class Tools {

    public static final int EDAD_MINIMA_COMPRENSION = 8;
    public static final int YEAR_MIN = 1900;
    public static int TIME_TO_EXIT = 2000; // Miliseconds
    private static FloatingActionButton fab;
    private static ScrollView mainScrollView;

    public static FloatingActionButton getFab() {
        return fab;
    }

    public static void setFab(FloatingActionButton fab) {
        Tools.fab = fab;
    }

    public static ScrollView getMainScrollView() {
        return mainScrollView;
    }

    public static void setMainScrollView(ScrollView mainScrollView) {
        Tools.mainScrollView = mainScrollView;
    }

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
        createSpinnerBase(a, view, idSpinner, R.layout.custom_spinner, values, 0);
    }

    public static void createSpinnerCompact(Activity a, View view, @IdRes int idSpinner, List<KeyValuePair> values, int customWidth) {
        createSpinnerBase(a, view, idSpinner, R.layout.compact_spinner, values, customWidth);
    }

    private static void createSpinnerBase(Activity a, View view, @IdRes int idSpinner, @LayoutRes int idLayoutSpinner, List<KeyValuePair> values, int customWidth) {
        Spinner spinner = view.findViewById(idSpinner);
        spinner.setAdapter(new SpinnerAdapter(a, idLayoutSpinner, values)); //if you r using fragment
        if (customWidth > 0) {
            spinner.setLayoutParams(new LinearLayout.LayoutParams((int) convertDpToPixel(a, customWidth), ViewGroup.LayoutParams.WRAP_CONTENT));
        }
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

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(Context context, float px) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    /**
     * POG - Crea una cadena a partir de un número rellenandolo con un caracter al principio
     *
     * @param number     Número que rellenar
     * @param beforeChar Carácter que se insertará
     * @param maxLength  Máximo de largo que tendrá para insertar el beforeChar
     * @return String
     */
    public static String fillStringByBeforeChar(Object number, String beforeChar,
                                                Integer maxLength) {
        if (number == null) {
            return "";
        }
        StringBuilder retorno = new StringBuilder();
        retorno.append(number);
        if (retorno.length() < Math.abs(maxLength)) {
            do {
                retorno.insert(0, beforeChar);
            } while (retorno.length() < Math.abs(maxLength));
        }
        return retorno.toString();
    }

    /**
     * POG - Crea una cadena a partir de un número rellenandolo con un caracter al final
     *
     * @param number    Número que rellenar
     * @param afterChar Carácter que se insertará
     * @param maxLength Máximo de largo que tendrá para insertar el beforeChar
     * @return String
     */
    public static String fillStringByAfterChar(Object number, String afterChar, Integer maxLength) {
        if (number == null) {
            return "";
        }
        StringBuilder retorno = new StringBuilder();
        retorno.append(number);
        if (retorno.length() < Math.abs(maxLength)) {
            do {
                retorno.append(afterChar);
            } while (retorno.length() < Math.abs(maxLength));
        }
        return retorno.toString();
    }

    public static List<KeyValuePair> getDays() {
        List<KeyValuePair> retorno = new ArrayList<>();
        for (int day = 1; day < 32; day++) {
            retorno.add(new KeyValuePair(day, fillStringByBeforeChar(day, "0", 2)));
        }
        return retorno;
    }

    public static List<KeyValuePair> getMonths(Context c) {
        List<KeyValuePair> retorno = new ArrayList<>();
        String[] months = c.getResources().getStringArray(R.array.list_months);
        for (int mth = 1; mth < 13; mth++) {
            retorno.add(new KeyValuePair(mth, months[mth - 1]));
        }
        return retorno;
    }

    public static List<KeyValuePair> getYears() {
        List<KeyValuePair> retorno = new ArrayList<>();
        int diff = Calendar.getInstance().get(Calendar.YEAR) - EDAD_MINIMA_COMPRENSION;
        for (int year = diff; year > YEAR_MIN; year--) {
            retorno.add(new KeyValuePair(year, String.valueOf(year)));
        }
        return retorno;
    }
}
