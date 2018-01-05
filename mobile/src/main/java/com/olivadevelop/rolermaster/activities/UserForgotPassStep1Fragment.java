package com.olivadevelop.rolermaster.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.CustomFragment;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.NavigationFragment;
import com.olivadevelop.rolermaster.tools.Tools;

public class UserForgotPassStep1Fragment extends CustomFragment {

    private OnFragmentInteractionListener mListener;
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

    @Override
    public void onClick(View v) {
        /*if (v == Tools.getFab()) {
            Navigation.getInstance().navigate(UserLoginFragment.class);
        } else */
        if (v == btnRecovery) {
            if (validateCode()) {
                Navigation.getInstance().navigate(NavigationFragment.USER_FORGOT_PASS_STEP2_FRAGMENT);
            } else {
                Tools.Logger(this, R.string.login_user_fail_login);
            }
        }
    }

    private boolean validateCode() {
        return true;
    }
}
