package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.persistence.controllers.TestController;
import com.olivadevelop.rolermaster.persistence.entities.TestEntity;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePair;

import java.util.List;

public class UserSignUpFragment extends CustomFragment {

    private TestController testController;

    public UserSignUpFragment() {
        super();
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
    public void onClick(View v) {

    }

    @Override
    protected void setFabIconFunction() {
        super.setFabIconFunction();
        Tools.getFab().hide();
    }
}
