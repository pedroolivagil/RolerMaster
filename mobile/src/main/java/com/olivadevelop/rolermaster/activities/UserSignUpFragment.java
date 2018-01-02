package com.olivadevelop.rolermaster.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.persistence.Entity;
import com.olivadevelop.rolermaster.persistence.TestEntity;
import com.olivadevelop.rolermaster.tools.CustomFragment;

import java.util.ArrayList;
import java.util.List;


public class UserSignUpFragment extends CustomFragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private List<Entity> values;

    public UserSignUpFragment() {
        values = new ArrayList<Entity>();
        values.add(new TestEntity("prueba 1"));
        values.add(new TestEntity("prueba 2"));
        values.add(new TestEntity("prueba 3"));
        values.add(new TestEntity("prueba 4"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_sign_up, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createSpinner(this.getActivity(), view, R.id.signup_user_country, values);
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
}
