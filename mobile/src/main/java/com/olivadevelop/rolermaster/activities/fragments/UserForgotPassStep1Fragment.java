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

public class UserForgotPassStep1Fragment extends CustomFragment {

    private Button btnRecovery;

    public UserForgotPassStep1Fragment() {
        // Required empty public constructor
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
        view = inflater.inflate(R.layout.fragment_user_forgot_pass_step1, container, false);
        setTitle(R.string.forgot_pass_title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnRecovery = (Button) getActivity().findViewById(R.id.btnRecovery2);
        if (btnRecovery != null) {
            btnRecovery.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnRecovery) {
            if (validateCode()) {
                Navigation.getInstance().navigate(NavigationFragment.USER_FORGOT_PASS_STEP2_FRAGMENT);
            } else {
                Tools.LoggerSnack(v, this, R.string.forgot_pass_code_invalid);
            }
        }
    }

    private boolean validateCode() {
        return true;
    }
}
