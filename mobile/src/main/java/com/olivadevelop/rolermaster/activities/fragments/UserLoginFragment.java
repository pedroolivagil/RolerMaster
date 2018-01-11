package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.NavigationFragment;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.EnumBundle;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

public class UserLoginFragment extends CustomFragment {

    private Button btnLogin;
    private EditText loginUser;
    private EditText loginPass;
    private TextView recoveryPass;

    public UserLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_login, container, false);
        setTitle(R.string.login_title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnLogin = findViewById(R.id.btnLogin);
        loginUser = findViewById(R.id.login_user);
        loginPass = findViewById(R.id.login_pass);
        recoveryPass = findViewById(R.id.recoveryPass);
        if (Tools.isNotNull(recoveryPass)) {
            recoveryPass.setOnClickListener(this);
        }
        if (Tools.isNotNull(loginUser) && Tools.isNotNull(_args)) {
            loginUser.setText(_args.getString(EnumBundle.FORGOT_PASS_EMAIL));
        }
        if (Tools.isNotNull(btnLogin)) {
            btnLogin.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == Tools.getFab()) {
            Navigation.getInstance().navigate(NavigationFragment.USER_SIGN_UP_FRAGMENT);
        } else if (v == btnLogin) {
            if (validateUserLogin()) {
                if (Controllers.getInstance().getTestController().testLogin(loginUser.getText().toString(), loginPass.getText().toString())) {
                    /*Tools.LoggerSnack(v, this, "OK!");*/
                    Preferences.getInstance().editor().putString(EnumBundle.LOGIN_EMAIL, loginUser.getText().toString()).apply();
                } else {
                    Tools.LoggerSnack(v, this, R.string.login_user_fail_login_bad_user_pass);
                }
            } else {
                Tools.LoggerSnack(v, this, R.string.login_user_fail_login);
            }
        } else if (v == recoveryPass) {
            Navigation.getInstance().navigate(NavigationFragment.USER_FORGOT_PASS_FRAGMENT);
        }
    }

    @Override
    protected void setFabIconFunction() {
        super.setFabIconFunction();
        Tools.getFab().setImageResource(R.drawable.account_plus_white);
        Tools.getFab().setOnClickListener(this);
        Tools.getFab().show();
    }

    private boolean validateUserLogin() {
        boolean retorno = false;
        if (Tools.isNotNull(loginUser) && Tools.isNotNull(loginPass)) {
            retorno = true;
        }
        return retorno;
    }
}
