package com.olivadevelop.rolermaster.tools.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.intefraces.ActionRolerMaster;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 17/01/2018.
 */

public class Alert {
    private static final Alert ourInstance = new Alert();

    public static Alert getInstance() {
        return ourInstance;
    }

    private AlertDialog loadingDialog;

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

        Button accept = view.findViewById(R.id.alert_dialog_action_ok);
        accept.setOnClickListener(
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

    private AlertDialog confirm(Activity a, String title, String text, final ActionAlert actionAlert) {
        final View view = a.getLayoutInflater().inflate(R.layout.alert_dialog_confirm, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        TextView titleView = view.findViewById(R.id.alert_dialog_title);
        titleView.setText(title);

        TextView textView = view.findViewById(R.id.alert_dialog_text);
        textView.setText(text);

        Button cancel = view.findViewById(R.id.alert_dialog_action_cancel);
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                }
        );

        Button accept = view.findViewById(R.id.alert_dialog_action_ok);
        accept.setOnClickListener(
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

    public void errorDialog(Activity a, Tools.Error error, String text, ActionAlert actionAlert) {
        StringBuilder title = new StringBuilder();
        title.append(a.getString(R.string.alert_dialog_title_error));
        title.append(" ");
        title.append(error.getVal());
        errorInfo(a, title.toString(), text, a.getResources().getColor(R.color.alertDialogError), actionAlert).show();
    }

    public void errorDialog(Activity a, String text, ActionAlert actionAlert) {
        errorInfo(a, a.getString(R.string.alert_dialog_title_error), text, a.getResources().getColor(R.color.alertDialogError), actionAlert).show();
    }

    public void confirmDialog(Activity a, @StringRes int title, @StringRes int text, ActionAlert trueActionAlert) {
        confirmDialog(a, a.getString(title), a.getString(text), trueActionAlert);
    }

    public void confirmDialog(Activity a, String title, String text, ActionAlert trueActionAlert) {
        confirm(a, title, text, trueActionAlert).show();
    }

    private AlertDialog loadingDialog(Activity a) {
        final View view = a.getLayoutInflater().inflate(R.layout.alert_dialog_loader, null);
        ProgressBar barInverted = view.findViewById(R.id.alert_dialog_progressbar_invert);
        barInverted.setRotation(180);
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setView(view);
        builder.setCancelable(false);
        return builder.create();
    }

    public void showLoadingDialog() {
        Context c = loadingDialog.getContext();
        loadingDialog.show();
        Window window = loadingDialog.getWindow();
        if (window != null) {
            window.setLayout((int) Tools.convertDpToPixel(c, 100), (int) Tools.convertDpToPixel(c, 100));
            window.setBackgroundDrawable(null);
        }
    }

    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }

    public void setLoadingDialog(Activity a) {
        this.loadingDialog = loadingDialog(a);
    }

    public static class ActionAlert implements ActionRolerMaster {
        @Override
        public void run() {

        }
    }
}
