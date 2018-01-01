package com.olivadevelop.rolermaster.tools;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.olivadevelop.rolermaster.R;

import java.util.concurrent.Callable;

/**
 * Created by Oliva on 29/12/2017.
 */

public abstract class Tools {

    public static int TIME_TO_EXIT = 2000; // Miliseconds

    public static void newBooleanDialog(Context c, int idTitle, int idMessage, final Callable<Void> funcPos) {
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

    public static void newInfoDialog(Context c, int idTitle, int idMessage, final Callable<Void> funcPos) {
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

    public static void Logger(Fragment c, String text) {
        Toast.makeText(c.getContext(), text, Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), text);
    }

    public static void Logger(Context c, int idText) {
        Toast.makeText(c, c.getString(idText), Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), c.getString(idText));
    }

    public static void Logger(Fragment c, int idText) {
        Toast.makeText(c.getContext(), c.getContext().getString(idText), Toast.LENGTH_LONG).show();
        Log.i(c.getClass().getSimpleName(), c.getString(idText));
    }

    public static void navigateFragment(FragmentManager fragmentManager, Class fragmentClass) {
        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.content_layout, fragment).commit();
        } catch (Exception e) {
            Log.i("Error Navigate -> ", e.getMessage());
        }
    }

    public static boolean isNull(TextView view) {
        return view == null || view.getText() == null || view.getText().toString().equals("");
    }

    public static boolean isNotNull(TextView view) {
        return !isNull(view);
    }
}
