package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.NavigationFragment;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.BundleLabels;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;

import static com.olivadevelop.rolermaster.tools.Tools.*;

public class UserForgotPassFragment extends CustomFragment {
    private Button btnRecovery;
    private EditText etEmail;

    public UserForgotPassFragment() {
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
        view = inflater.inflate(R.layout.fragment_user_forgot_pass, container, false);
        setTitle(R.string.forgot_pass_title, R.string.forgot_pass_title_step1);
        etEmail = findViewById(R.id.forgot_pass_mail);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnRecovery = (Button) getActivity().findViewById(R.id.btnRecovery1);
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
            if (validateEmail()) {
                Bundle args = new Bundle();
                args.putString(BundleLabels.EMAIL, etEmail.getText().toString());
                Navigation.getInstance().navigate(NavigationFragment.USER_FORGOT_PASS_STEP1_FRAGMENT);
            } else {
                LoggerSnack(v, this, R.string.forgot_pass_email_invalid);
            }
        }
    }

    private boolean validateEmail() {
        boolean retorno = false;
        if (isNotNull(etEmail) && isEmailValid(etEmail)) {
            retorno = true;
        }
        return retorno;
    }

    @Override
    protected void setFabIconFunction() {
        getFab().hide();
    }
}
