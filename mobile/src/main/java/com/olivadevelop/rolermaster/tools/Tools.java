package com.olivadevelop.rolermaster.tools;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.olivadevelop.rolermaster.R;

import java.util.concurrent.Callable;

/**
 * Created by Oliva on 29/12/2017.
 */

public abstract class Tools {

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
    }

    public static void Logger(Context c, int idText) {
        Toast.makeText(c, c.getString(idText), Toast.LENGTH_LONG).show();
    }
}
