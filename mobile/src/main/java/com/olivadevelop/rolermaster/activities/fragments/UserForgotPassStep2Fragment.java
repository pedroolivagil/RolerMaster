package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.NavigationFragment;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.EnumBundle;

public class UserForgotPassStep2Fragment extends CustomFragment {
    private Button btnRecovery;

    public UserForgotPassStep2Fragment() {
        super();
        setIgnoreNavigation(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_forgot_pass_step2, container, false);
        String subtitle = "";
        if (Tools.isNotNull(_args)) {
            subtitle = _args.getString(EnumBundle.FORGOT_PASS_EMAIL);
        }
        setTitle(getString(R.string.forgot_pass_title), getString(R.string.forgot_pass_title_step3), subtitle);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnRecovery = (Button) getActivity().findViewById(R.id.btnRecovery3);
        if (btnRecovery != null) {
            btnRecovery.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        /*if (v == Tools.getFab()) {
            Navigation.getInstance().navigate(UserLoginFragment.class);
        } else */
        if (v == btnRecovery) {
            String msj = validatePasswords();
            if (Tools.isNull(msj)) {
                String subtitle = "";
                if (Tools.isNotNull(_args)) {
                    subtitle = _args.getString(EnumBundle.FORGOT_PASS_EMAIL);
                }
                Bundle args = new Bundle();
                args.putString(EnumBundle.FORGOT_PASS_EMAIL, subtitle);
                Navigation.getInstance().navigate(NavigationFragment.USER_FORGOT_PASS_STEP3_FRAGMENT, args);
            } else {
                Tools.LoggerSnack(v, this, msj);
            }
        }
    }

    private String validatePasswords() {
        return null;
    }
}
