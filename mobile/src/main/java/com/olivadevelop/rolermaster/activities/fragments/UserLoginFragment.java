package com.olivadevelop.rolermaster.activities.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.SessionManager;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;

public class UserLoginFragment extends CustomFragment {

    private Button btnLogin;
    private EditText loginUser;
    private EditText loginPass;
    private TextView recoveryPass;

    public UserLoginFragment() {
        super();
        idView = R.layout.fragment_user_login;
    }

    @Override
    protected void actionsOnCreateView() {
        super.actionsOnCreateView();
        setTitle(R.string.login_title);
    }

    @Override
    protected void actionsOnActivityCreated() {
        super.actionsOnActivityCreated();
        btnLogin = findViewById(R.id.btnLogin);
        loginUser = findViewById(R.id.login_user);
        loginPass = findViewById(R.id.login_pass);
        recoveryPass = findViewById(R.id.recoveryPass);
        if (Tools.isNotNull(recoveryPass)) {
            recoveryPass.setOnClickListener(this);
        }
        if (Tools.isNotNull(btnLogin)) {
            btnLogin.setOnClickListener(this);
        }

        /*para el test*/
        loginUser.setText("testuser");
        loginPass.setText("1234");
    }

    @Override
    protected void actionsFab(View v) {
        super.actionsFab(v);
        Navigation.getInstance().navigate(Navigation.Page.USER_SIGN_UP_FRAGMENT);
    }

    @Override
    protected void actionsOnClick(View v) {
        super.actionsOnClick(v);
        if (v == btnLogin) {
            Tools.showModal(this.getActivity(), true);
            Tools.hideVirtualKeyboard(this.getActivity());
            Tools.LoggerSnack(v, this, R.string.login_user_logging);
            if (validateUserLogin()) {
                if (SessionManager.getInstance().login(loginUser.getText().toString().trim(), loginPass.getText().toString().trim())) {
                    Navigation.getInstance().navigateActivityThread(Navigation.Page.HOME_ACTIVITY, this.getContext(), 3000, null);
                } else {
                    Tools.hideModal(this.getActivity(), true);
                    Tools.LoggerSnack(v, this, R.string.login_user_fail_login_bad_user_pass);
                }
            } else {
                Tools.hideModal(this.getActivity(), true);
                Tools.LoggerSnack(v, this, R.string.login_user_fail_login);
            }
        } else if (v == recoveryPass) {
            Navigation.getInstance().navigate(Navigation.Page.USER_FORGOT_PASS_FRAGMENT);
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
