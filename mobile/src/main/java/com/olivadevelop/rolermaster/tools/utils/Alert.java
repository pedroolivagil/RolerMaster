package com.olivadevelop.rolermaster.tools.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 17/01/2018.
 */

public class Alert {
    private static final Alert ourInstance = new Alert();

    public static Alert getInstance() {
        return ourInstance;
    }

    private Alert() {
    }

    private AlertDialog errorInfo(Activity a, String title, String text, int color, final ActionAlert actionAlert) {
        final View view = a.getLayoutInflater().inflate(R.layout.alert_dialog_error, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        TextView titleView = view.findViewById(R.id.alert_dialog_title);
        titleView.setText(title);
        titleView.setBackgroundColor(color);

        TextView textView = view.findViewById(R.id.alert_dialog_text);
        textView.setText(text);

        Button cancel = view.findViewById(R.id.alert_dialog_action_ok);
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actionAlert.run();
                        alertDialog.dismiss();
                    }
                }
        );
        return alertDialog;
    }

    public void infoDialog(Activity a, String text, ActionAlert actionAlert) {
        errorInfo(a, a.getString(R.string.alert_dialog_title_info), text, a.getResources().getColor(R.color.alertDialogInfo), actionAlert).show();
    }

    public void errorDialog(Activity a, String text, ActionAlert actionAlert) {
        errorInfo(a, a.getString(R.string.alert_dialog_title_error), text, a.getResources().getColor(R.color.alertDialogError), actionAlert).show();
    }

    public static class ActionAlert {

        public void run() {

        }
    }
}
