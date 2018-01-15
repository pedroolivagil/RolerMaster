package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.NavigationFragment;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

import static com.olivadevelop.rolermaster.tools.Tools.LoggerSnack;
import static com.olivadevelop.rolermaster.tools.Tools.getFab;
import static com.olivadevelop.rolermaster.tools.Tools.isEmailValid;

public class UserForgotPassFragment extends CustomFragment {
    private Button btnRecovery;
    private EditText etEmail;

    public UserForgotPassFragment() {
        super();
        idView = R.layout.fragment_user_forgot_pass;
        setIgnoreNavigation(true);
    }

    @Override
    protected void actionsOnCreateView() {
        super.actionsOnCreateView();
        setTitle(getString(R.string.forgot_pass_title), getString(R.string.forgot_pass_title_step1));
    }

    @Override
    protected void actionsOnActivityCreated() {
        super.actionsOnActivityCreated();
        btnRecovery = findViewById(R.id.btnRecovery1);
        etEmail = findViewById(R.id.forgot_pass_mail);
        if (Tools.isNotNull(_args) && Tools.isNotNull(etEmail)) {
            etEmail.setText(_args.getString(Preferences.EnumBundle.FORGOT_PASS_EMAIL));
        }
        if (Tools.isNotNull(btnRecovery)) {
            btnRecovery.setOnClickListener(this);
        }
    }

    @Override
    protected void actionsOnClick(View v) {
        super.actionsOnClick(v);
        if (v == btnRecovery) {
            if (validateEmail()) {
                Bundle args = new Bundle();
                args.putString(Preferences.EnumBundle.FORGOT_PASS_EMAIL, etEmail.getText().toString());
                Navigation.getInstance().navigate(NavigationFragment.USER_FORGOT_PASS_STEP1_FRAGMENT, args);
            } else {
                LoggerSnack(v, this, R.string.forgot_pass_email_invalid);
            }
        }
    }

    private boolean validateEmail() {
        boolean retorno = false;
        if (isEmailValid(etEmail)) {
            retorno = true;
        }
        return retorno;
    }

    @Override
    protected void setFabIconFunction() {
        getFab().hide();
    }
}
