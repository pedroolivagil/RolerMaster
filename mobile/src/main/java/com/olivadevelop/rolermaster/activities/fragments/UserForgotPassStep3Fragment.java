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
import com.olivadevelop.rolermaster.tools.utils.BundleLabels;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;


public class UserForgotPassStep3Fragment extends CustomFragment {

    private Button btnRecovery;

    public UserForgotPassStep3Fragment() {
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
        view = inflater.inflate(R.layout.fragment_user_forgot_pass_step3, container, false);

        String subtitle = "";
        if (Tools.isNotNull(_args)) {
            subtitle = _args.getString(BundleLabels.FORGOT_PASS_EMAIL);
        }
        setTitle(getString(R.string.forgot_pass_title), getString(R.string.forgot_pass_title_step4), subtitle);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnRecovery = (Button) getActivity().findViewById(R.id.btnRecovery4);
        if (btnRecovery != null) {
            btnRecovery.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnRecovery) {
            String subtitle = "";
            if (Tools.isNotNull(_args)) {
                subtitle = _args.getString(BundleLabels.FORGOT_PASS_EMAIL);
            }
            Bundle args = new Bundle();
            args.putString(BundleLabels.FORGOT_PASS_EMAIL, subtitle);
            Navigation.getInstance().navigate(NavigationFragment.USER_LOGIN_FRAGMENT, args);
        }
    }
}
