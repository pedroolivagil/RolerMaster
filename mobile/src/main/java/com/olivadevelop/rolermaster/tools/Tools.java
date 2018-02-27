package com.olivadevelop.rolermaster.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.KeyValuePair;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Utils;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.SpinnerAdapter;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 29/12/2017.
 * RolerMaster
 */

public abstract class Tools extends Utils {

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

    public static void disableView(Activity a) {
        a.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void enableView(Activity a) {
        a.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        a.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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

    public static void Logger(CustomFragment c, String text) {
        Toast.makeText(c.getContext(), text, Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), text);
    }

    public static void Logger(CustomFragment c, @StringRes int idText) {
        Toast.makeText(c.getContext(), c.getContext().getString(idText), Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), c.getString(idText));
    }

    public static void Logger(CustomFragment c, Exception e) {
        Toast.makeText(c.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), e.getLocalizedMessage());
        e.printStackTrace();
    }

    public static void LoggerSnack(View view, Activity c, @StringRes int idText) {
        LoggerSnack(view, c, c.getString(idText));
    }

    public static void LoggerSnack(View view, Activity c, String text) {
        customSnackBar(view, text);
        Log.i(c.getClass().getSimpleName(), text);
    }


    public static void LoggerSnack(View view, CustomFragment c, @StringRes int idText) {
        LoggerSnack(view, c, c.getString(idText));
    }

    public static void LoggerSnack(View view, CustomFragment c, String text) {
        customSnackBar(view, text);
        Log.i(c.getClass().getSimpleName(), text);
    }

    public static void LoggerSnackNotAvailable(View view, Activity c) {
        LoggerSnack(view, c, c.getString(R.string.option_not_available));
    }

    public static void LoggerSnackNotAvailable(View view, CustomFragment c) {
        LoggerSnack(view, c, c.getString(R.string.option_not_available));
    }

    public static void LoggerSnack(View v, CustomFragment c, String text, int icon) {
        customSnackBar(v, text, c.getActivity(), icon);
    }


    public static void createSpinner(Activity a, View view, @IdRes int idSpinner, List<KeyValuePair> values) {
        createSpinnerBase(a, view, idSpinner, R.layout.custom_spinner, values, 0);
    }

    public static void createSpinnerCompact(Activity a, View view, @IdRes int idSpinner, List<KeyValuePair> values, int customWidth) {
        createSpinnerBase(a, view, idSpinner, R.layout.compact_spinner, values, customWidth);
    }

    /**
     * Personalizamos el snackbar
     *
     * @param v    vista padre dónde se mostrará el snackbar
     * @param text texto que mostrará el snackbar
     */
    private static void customSnackBar(View v, String text) {
        customSnackBar(v, text, null, null);
    }

    private static void customSnackBar(View v, String text, Activity a, @IdRes Integer icon) {
        Snackbar snackBar = Snackbar.make(v, text, Snackbar.LENGTH_LONG);
        View view = snackBar.getView();
        view.bringToFront();
        android.support.design.widget.CoordinatorLayout.LayoutParams params = (android.support.design.widget.CoordinatorLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        view.setLayoutParams(params);
        view.setBackgroundResource(R.color.colorPrimary);
        if (isNotNull(icon)) {
            TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
            textView.setCompoundDrawablesWithIntrinsicBounds(icon.intValue(), 0, 0, 0);
            textView.setCompoundDrawablePadding(a.getResources().getDimensionPixelOffset(R.dimen.snackbar_icon_padding));
        }
        snackBar.show();
    }

    /**
     * Rellena los spinners que se le pasan por parámetro
     *
     * @param a               Activity
     * @param view            vista dónde se obtiene el spinner
     * @param idSpinner       id del spinner
     * @param idLayoutSpinner la capa que se usará para renderizar el spinner
     * @param values          la lista de valores KeyValuePair
     * @param customWidth     el ancho que ocupará en la vista, en DP
     */
    private static void createSpinnerBase(Activity a, View view, @IdRes int idSpinner, @LayoutRes int idLayoutSpinner, List<KeyValuePair> values, int customWidth) {
        Spinner spinner = view.findViewById(idSpinner);
        spinner.setAdapter(new SpinnerAdapter(a, idLayoutSpinner, values)); //if you r using fragment
        if (customWidth > 0) {
            spinner.setLayoutParams(new LinearLayout.LayoutParams((int) convertDpToPixel(a, customWidth), ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    /**
     * Convierte una lista de ? a una lista de KeyValuePair
     *
     * @param listEntities  Lista de objetos
     * @param propertyKey   propiedad del objeto que es clave
     * @param propertyValue propiedad del objeto que es el valor
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
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
     * @param obj        Número, string,... que rellenar
     * @param beforeChar Carácter que se insertará
     * @param maxLength  Máximo de largo que tendrá para insertar el beforeChar
     * @return String
     */
    public static String fillStringByBeforeChar(Object obj, String beforeChar, Integer maxLength) {
        if (obj == null) {
            return "";
        }
        StringBuilder retorno = new StringBuilder();
        retorno.append(obj);
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

    /**
     * Devuelve una lista de días con la clase KeyValuePair
     *
     * @return List
     */
    public static List<KeyValuePair> getDays() {
        List<KeyValuePair> retorno = new ArrayList<>();
        for (int day = 1; day < 32; day++) {
            retorno.add(new KeyValuePair(day, fillStringByBeforeChar(day, "0", 2)));
        }
        return retorno;
    }

    /**
     * Devuelve una lista de meses con la clase KeyValuePair
     *
     * @return List
     */
    public static List<KeyValuePair> getMonths(Context c) {
        List<KeyValuePair> retorno = new ArrayList<>();
        String[] months = c.getResources().getStringArray(R.array.list_months);
        for (int mth = 1; mth < 13; mth++) {
            retorno.add(new KeyValuePair(mth, months[mth - 1]));
        }
        return retorno;
    }

    /**
     * Devuelve una lista de años con la clase KeyValuePair
     *
     * @return List
     */
    public static List<KeyValuePair> getYears() {
        List<KeyValuePair> retorno = new ArrayList<>();
        int diff = Calendar.getInstance().get(Calendar.YEAR) - Constant.MINIMUM_AGE_COMPREHENSION;
        for (int year = diff; year > Constant.YEAR_MIN; year--) {
            retorno.add(new KeyValuePair(year, String.valueOf(year)));
        }
        return retorno;
    }

    public static String versionName(Context c) {
        String version = "0.0.1";
        try {
            PackageInfo pInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static void hideVirtualKeyboard(Activity c) {
        View view = c.getCurrentFocus();
        if (isNotNull(view)) {
            view.clearFocus();
            InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (isNotNull(imm)) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static String capitalize(CharSequence sequence) {
        StringBuilder retorno = new StringBuilder();
        if (isNotNull(sequence)) {
            retorno.append(sequence.charAt(0));
            if (sequence.length() > 1) {
                retorno.append(sequence.subSequence(1, sequence.length()));
            }
        }
        return retorno.toString();
    }

    public static void getDeviceEmails(Activity a) {
        Intent googlePicker = AccountPicker.newChooseAccountIntent(null, null, new String[]{
                GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE
        }, true, null, null, null, null);
        a.startActivityForResult(googlePicker, Constant.REQUEST_CODE);
    }

    private static boolean isNetworkActive(Context c) {
        ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = null;
        if (connectivityManager != null) {
            actNetInfo = connectivityManager.getActiveNetworkInfo();
        }
        return (actNetInfo != null && actNetInfo.isConnected());
    }

    public static Boolean isNetworkAvailable(Context c) {
        if (isNetworkActive(c)) {
            try {
                Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 " + Constant.SERVER);
                int val = p.waitFor();
                return (val == 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(number.toString(16));
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String str) {
        String pass = Constant.CRYPT_KEY + "" + str;
        return getMD5(pass);
    }

    public static String generateID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateID(int length) {
        return generateID().substring(0, length);
    }


    public static String getCurrentDate() {
        return formatDate(new Date());
    }

    public static String getCurrentDate(String pattern) {
        return formatDate(new Date(), pattern);
    }

    public static String formatDate(Date date) {
        return formatDate(date, "dd-MM-yyyy");
    }

    public static String formatDate(Date date, String pattern) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }

    public static Date getDateFromStringLong(String stringTime) {
        Date retorno = null;
        if (Tools.isNotNull(stringTime)) {
            try {
                retorno = new Date(Long.valueOf(stringTime));
            } catch (NumberFormatException e) {
            }
        }
        return retorno;
    }
}
