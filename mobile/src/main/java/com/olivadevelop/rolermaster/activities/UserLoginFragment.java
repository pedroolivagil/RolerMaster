package com.olivadevelop.rolermaster.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.olivadevelop.rolermaster.MainActivity;
import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.CustomFragment;
import com.olivadevelop.rolermaster.tools.Tools;

public class UserLoginFragment extends CustomFragment {

    private OnFragmentInteractionListener mListener;

    private Button btnLogin;
    private EditText loginUser;
    private EditText loginPass;
    private TextView recoveryPass;

    public UserLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFabIconFunction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnLogin = (Button) getActivity().findViewById(R.id.btnLogin);
        loginUser = (EditText) getActivity().findViewById(R.id.login_user);
        loginPass = (EditText) getActivity().findViewById(R.id.login_pass);
        recoveryPass = (TextView) getActivity().findViewById(R.id.recoveryPass);
        if (recoveryPass != null) {
            recoveryPass.setOnClickListener(this);
        }
        if (btnLogin != null) {
            btnLogin.setOnClickListener(this);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v == MainActivity.getFab()) {
            Tools.navigateFragment(fragmentManager, UserSignUpFragment.class);
        } else if (v == btnLogin) {
            if (validateUserLogin()) {
                Tools.Logger(this, "User: " + loginUser.getText() + "; Pass: " + loginPass.getText());
            } else {
                Tools.Logger(this, R.string.login_user_fail_login);
            }
        } else if (v == recoveryPass) {
            Tools.Logger(this, "Recovery btn");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setFabIconFunction() {
        MainActivity.getFab().setImageResource(R.drawable.account_plus_white);
        MainActivity.getFab().setOnClickListener(this);
    }

    private boolean validateUserLogin() {
        boolean retorno = false;
        if (Tools.isNotNull(loginUser) && Tools.isNotNull(loginPass)) {
            retorno = true;
        }
        return retorno;
    }
}
