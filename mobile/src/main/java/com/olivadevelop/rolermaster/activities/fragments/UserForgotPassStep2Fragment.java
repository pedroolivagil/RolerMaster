package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

public class UserForgotPassStep2Fragment extends CustomFragment {
    private Button btnRecovery;

    public UserForgotPassStep2Fragment() {
        super();
        idView = R.layout.fragment_user_forgot_pass_step2;
        setIgnoreNavigation(true);
    }

    @Override
    protected void actionsOnCreateView() {
        super.actionsOnCreateView();
        String subtitle = "";
        if (Tools.isNotNull(_args)) {
            subtitle = _args.getString(Preferences.EnumBundle.FORGOT_PASS_EMAIL);
        }
        setTitle(getString(R.string.forgot_pass_title), getString(R.string.forgot_pass_title_step3), subtitle);
    }

    @Override
    protected void actionsOnActivityCreated() {
        super.actionsOnActivityCreated();
        btnRecovery = findViewById(R.id.btnRecovery3);
        if (btnRecovery != null) {
            btnRecovery.setOnClickListener(this);
        }
    }

    @Override
    protected void actionsOnClick(View v) {
        super.actionsOnClick(v);
        if (v == btnRecovery) {
            String msj = validatePasswords();
            if (Tools.isNull(msj)) {
                String subtitle = "";
                if (Tools.isNotNull(_args)) {
                    subtitle = _args.getString(Preferences.EnumBundle.FORGOT_PASS_EMAIL);
                }
                Bundle args = new Bundle();
                args.putString(Preferences.EnumBundle.FORGOT_PASS_EMAIL, subtitle);
                Navigation.getInstance().navigate(Navigation.Page.USER_FORGOT_PASS_STEP3_FRAGMENT, args);
            } else {
                Tools.LoggerSnack(v, this, msj);
            }
        }
    }

    private String validatePasswords() {
        return null;
    }
}
