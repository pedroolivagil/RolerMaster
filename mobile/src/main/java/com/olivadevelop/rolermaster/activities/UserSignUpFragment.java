package com.olivadevelop.rolermaster.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.persistence.controllers.TestController;
import com.olivadevelop.rolermaster.persistence.entities.TestEntity;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePair;
import com.olivadevelop.rolermaster.tools.Tools;

import java.util.List;

public class UserSignUpFragment extends CustomFragment {

    private OnFragmentInteractionListener mListener;
    private TestController testController;

    public UserSignUpFragment() {
        testController = Controllers.getInstance().getTestController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_sign_up, container, false);
        setTitle(R.string.signup_title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<KeyValuePair> values = null;
        List<KeyValuePair> values2 = null;
        List<KeyValuePair> values3 = null;
        try {
            values = Tools.objToKeyValuePair(testController.findAll(), TestEntity.FIELD_KEY, TestEntity.FIELD_TEXTO);
            values2 = Tools.objToKeyValuePair(testController.findAll(), TestEntity.FIELD_KEY, TestEntity.FIELD_TEXTO);
            values3 = Tools.objToKeyValuePair(testController.findAll(), TestEntity.FIELD_KEY, TestEntity.FIELD_TEXTO);
        } catch (NoSuchFieldException e) {
            Tools.Logger(this, e);
        } catch (IllegalAccessException e) {
            Tools.Logger(this, e);
        }
        // Rellenamos el spinner de los países
        Tools.createSpinner(this.getActivity(), view, R.id.signup_user_country, values);
        // Rellenamos el spinner de los géneros
        Tools.createSpinner(this.getActivity(), view, R.id.signup_user_gender, values2);
        // Rellenamos el spinner del tipo de usuario
        Tools.createSpinner(this.getActivity(), view, R.id.signup_user_typeuser, values3);
        // rellenamos los spinners de la fecha de nacimiento
        Tools.createSpinnerCompact(this.getActivity(), view.findViewById(R.id.signup_spinners_birth), R.id.signup_user_birth1, Tools.getDays(), 70);
        Tools.createSpinnerCompact(this.getActivity(), view.findViewById(R.id.signup_spinners_birth), R.id.signup_user_birth2, Tools.getMonths(this.getContext()), 155);
        Tools.createSpinnerCompact(this.getActivity(), view.findViewById(R.id.signup_spinners_birth), R.id.signup_user_birth3, Tools.getYears(), 85);
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

    @Override
    protected void setFabIconFunction() {
        super.setFabIconFunction();
        Tools.getFab().hide();
    }
}
