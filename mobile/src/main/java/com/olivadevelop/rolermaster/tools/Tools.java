package com.olivadevelop.rolermaster.tools;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePair;
import com.olivadevelop.rolermaster.tools.utils.SpinnerAdapter;

import org.apache.commons.validator.routines.EmailValidator;

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
    public static int TIME_SPLASH = 2000; // Miliseconds
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

    public static boolean isNotNull(EditText view) {
        return !isNull(view) && isNotNull(view.getText()) && isNotNull(view.getText().toString().trim());
    }

    public static boolean isEmailValid(EditText email) {
        return isEmailValid(email.getText().toString().trim());
    }

    public static boolean isEmailValid(String... emails) {
        boolean retorno = true;
        for (String email : emails) {
            if (!EmailValidator.getInstance().isValid(email)) {
                retorno = false;
                break;
            }
        }
        return retorno;
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
        Snackbar snackBar = Snackbar.make(v, text, Snackbar.LENGTH_LONG);
        View view = snackBar.getView();
        android.support.design.widget.CoordinatorLayout.LayoutParams params = (android.support.design.widget.CoordinatorLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        view.setLayoutParams(params);
        view.setBackgroundResource(R.color.colorPrimary);
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
        int diff = Calendar.getInstance().get(Calendar.YEAR) - EDAD_MINIMA_COMPRENSION;
        for (int year = diff; year > YEAR_MIN; year--) {
            retorno.add(new KeyValuePair(year, String.valueOf(year)));
        }
        return retorno;
    }

    /**
     * Redimensiona una imagen (drawable) y devuelve un objeto drawable
     *
     * @param ctx   Contexto
     * @param resId ID del drawable a redimensionar
     * @param w     Ancho deseado
     * @param h     Alto deseado
     * @return Drawable
     */
    public static Drawable resizeImage(Context ctx, int resId, int w, int h) {

        // cargamos la imagen de origen
        Bitmap BitmapOrg = BitmapFactory.decodeResource(ctx.getResources(),
                resId);

        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();

        // calculamos el escalado de la imagen destino
        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;

        // para poder manipular la imagen
        // debemos crear una matriz

        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        // volvemos a crear la imagen con los nuevos valores
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                width, height, matrix, true);

        // si queremos poder mostrar nuestra imagen tenemos que crear un
        // objeto drawable y así asignarlo a un botón, imageview...
        return new BitmapDrawable(resizedBitmap);

    }
}
