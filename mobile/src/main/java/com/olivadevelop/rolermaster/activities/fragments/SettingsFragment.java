package com.olivadevelop.rolermaster.activities.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Constant;
import com.olivadevelop.rolermaster.tools.SessionManager;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.layouts.BooleanSettingsView;
import com.olivadevelop.rolermaster.tools.layouts.ItemSettingsView;
import com.olivadevelop.rolermaster.tools.utils.Alert;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

public class SettingsFragment extends CustomFragment {

    private boolean firstLoad;
    private BooleanSettingsView optionNotificationMail;
    private BooleanSettingsView optionExportPdf;
    private BooleanSettingsView optionNotificationCalendar;
    private BooleanSettingsView optionNotificationSwatch;
    private BooleanSettingsView optionNotificationAutologin;
    private BooleanSettingsView optionNotificationApp;
    private ItemSettingsView optionDonate;
    private ItemSettingsView optionBuyLicenceApp;
    private ItemSettingsView optionPermisions;

    public SettingsFragment() {
        idView = R.layout.fragment_settings;
    }

    @Override
    protected void actionsOnCreateView() {
        super.actionsOnCreateView();
        setTitle(R.string.nav_tools);
        firstLoad = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tvVersion = findViewById(R.id.tvVersion);
        tvVersion.setText(Tools.versionName(this.getContext()));

        boolean autologin = Preferences.getPrefs().getBoolean(Preferences.EnumBundle.PREFS_AUTOLOGIN, false);
        boolean exportPdf = Preferences.getPrefs().getBoolean(Preferences.EnumBundle.PREFS_EXPORT_PDF, Constant.EXPORT_PDF_PARTIAL);
        boolean notifMail = Preferences.getPrefs().getBoolean(Preferences.EnumBundle.PREFS_NOTIF_EMAIL, false);
        boolean notifCalendar = Preferences.getPrefs().getBoolean(Preferences.EnumBundle.PREFS_NOTIF_CALENDAR, false);
        boolean notifSwatch = Preferences.getPrefs().getBoolean(Preferences.EnumBundle.PREFS_NOTIF_SWATCH, false);
        boolean notifApp = Preferences.getPrefs().getBoolean(Preferences.EnumBundle.PREFS_NOTIF_ROLERMASTER, false);
        firstLoad = true;

        optionNotificationAutologin = findViewById(R.id.optionNotificationAutologin);
        optionNotificationAutologin.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationAutologin.getSwValue().setChecked(autologin);

        optionExportPdf = findViewById(R.id.optionExportPdf);
        optionExportPdf.getSwValue().setOnCheckedChangeListener(this);
        optionExportPdf.getSwValue().setChecked(exportPdf);
        String text = (exportPdf == Constant.EXPORT_PDF_FULL) ? getString(R.string.settings_export_pdf_true) : getString(R.string.settings_export_pdf_false);
        optionExportPdf.getTvSubtitle().setText(text);

        optionNotificationApp = findViewById(R.id.optionNotificationApp);
        optionNotificationApp.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationApp.getSwValue().setChecked(notifApp);

        optionNotificationMail = findViewById(R.id.optionNotificationMail);
        optionNotificationMail.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationMail.getSwValue().setChecked(notifMail);

        optionNotificationCalendar = findViewById(R.id.optionNotificationCalendar);
        optionNotificationCalendar.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationCalendar.getSwValue().setChecked(notifCalendar);

        optionNotificationSwatch = findViewById(R.id.optionNotificationSwatch);
        optionNotificationSwatch.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationSwatch.getSwValue().setChecked(notifSwatch);

        optionPermisions = findViewById(R.id.optionPermisions);
        optionPermisions.setOnClickListener(this);

        optionDonate = findViewById(R.id.optionDonate);
        optionDonate.setOnClickListener(this);

        optionBuyLicenceApp = findViewById(R.id.optionBuyLicenceApp);
        optionBuyLicenceApp.setOnClickListener(this);

        firstLoad = false;

        if (!SessionManager.getInstance().isLogged()) {
            optionNotificationApp.setActive(false);
            optionNotificationAutologin.setActive(false);
            optionExportPdf.setActive(false);
            optionNotificationMail.setActive(false);
            optionNotificationCalendar.setActive(false);
            optionNotificationSwatch.setActive(false);
            optionDonate.setActive(false);
            optionBuyLicenceApp.setActive(false);
        }
    }

    @Override
    protected void actionsOnClick(View v) {
        super.actionsOnClick(v);
        if (v == optionDonate && optionDonate.isActive()) {
            Tools.LoggerSnackNotAvailable(view, this);
        } else if (v == optionBuyLicenceApp && optionBuyLicenceApp.isActive()) {
            Tools.LoggerSnackNotAvailable(view, this);
        } else if (v == optionPermisions && optionPermisions.isActive() && getActivity() != null) {
            Alert.getInstance().showLoadingDialog();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Alert.getInstance().hideLoadingDialog();
    }

    @Override
    protected void actionsOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        super.actionsOnCheckedChanged(compoundButton, b);
        if (!firstLoad) {
            if (optionNotificationAutologin.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(Preferences.EnumBundle.PREFS_AUTOLOGIN, b).apply();
            } else if (optionNotificationMail.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(Preferences.EnumBundle.PREFS_NOTIF_EMAIL, b).apply();
            } else if (optionNotificationCalendar.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(Preferences.EnumBundle.PREFS_NOTIF_CALENDAR, b).apply();
            } else if (optionNotificationSwatch.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(Preferences.EnumBundle.PREFS_NOTIF_SWATCH, b).apply();
            } else if (optionNotificationApp.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(Preferences.EnumBundle.PREFS_NOTIF_ROLERMASTER, b).apply();
            } else if (optionExportPdf.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(Preferences.EnumBundle.PREFS_EXPORT_PDF, b).apply();
                String text = (b == Constant.EXPORT_PDF_FULL) ? getString(R.string.settings_export_pdf_true) : getString(R.string.settings_export_pdf_false);
                optionExportPdf.getTvSubtitle().setText(text);
            }
            Tools.LoggerSnack(view, this, R.string.settings_changed_success);
        }
    }

    @Override
    protected void setFabIconFunction() {
        super.setFabIconFunction();
        Tools.getFab().hide();
    }

}
