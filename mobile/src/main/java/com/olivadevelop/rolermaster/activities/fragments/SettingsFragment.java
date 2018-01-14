package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.layouts.BooleanSettingsView;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.EnumBundle;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

public class SettingsFragment extends CustomFragment {

    private BooleanSettingsView optionNotificationMail;
    private BooleanSettingsView optionNotificationCalendar;
    private BooleanSettingsView optionNotificationSwatch;
    private BooleanSettingsView optionNotificationAutologin;

    private boolean firstLoad;

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

        boolean autologin = Preferences.getPrefs().getBoolean(EnumBundle.PREFS_AUTOLOGIN, false);
        boolean notifMail = Preferences.getPrefs().getBoolean(EnumBundle.PREFS_NOTIF_EMAIL, false);
        boolean notifCalendar = Preferences.getPrefs().getBoolean(EnumBundle.PREFS_NOTIF_CALENDAR, false);
        boolean notifSwatch = Preferences.getPrefs().getBoolean(EnumBundle.PREFS_NOTIF_SWATCH, false);
        firstLoad = true;

        optionNotificationAutologin = findViewById(R.id.optionNotificationAutologin);
        optionNotificationAutologin.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationAutologin.getSwValue().setChecked(autologin);

        optionNotificationMail = findViewById(R.id.optionNotificationMail);
        optionNotificationMail.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationMail.getSwValue().setChecked(notifMail);

        optionNotificationCalendar = findViewById(R.id.optionNotificationCalendar);
        optionNotificationCalendar.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationCalendar.getSwValue().setChecked(notifCalendar);

        optionNotificationSwatch = findViewById(R.id.optionNotificationSwatch);
        optionNotificationSwatch.getSwValue().setOnCheckedChangeListener(this);
        optionNotificationSwatch.getSwValue().setChecked(notifSwatch);

        firstLoad = false;
    }

    @Override
    protected void actionsOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        super.actionsOnCheckedChanged(compoundButton, b);
        if (!firstLoad) {
            if (optionNotificationAutologin.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(EnumBundle.PREFS_AUTOLOGIN, b).apply();
            }else if (optionNotificationMail.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(EnumBundle.PREFS_NOTIF_EMAIL, b).apply();
            } else if (optionNotificationCalendar.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(EnumBundle.PREFS_NOTIF_CALENDAR, b).apply();
            } else if (optionNotificationSwatch.getSwValue() == compoundButton) {
                Preferences.getInstance().editor().putBoolean(EnumBundle.PREFS_NOTIF_SWATCH, b).apply();
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
