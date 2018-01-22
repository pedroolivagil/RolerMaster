package com.olivadevelop.rolermaster.activities.fragments;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.persistence.controllers.TestController;
import com.olivadevelop.rolermaster.persistence.entities.old.TestEntity;
import com.olivadevelop.rolermaster.persistence.managers._RestService;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePair;

import java.util.ArrayList;
import java.util.List;

public class UserSignUpFragment extends CustomFragment {

    private TestController testController;

    public UserSignUpFragment() {
        super();
        setIgnoreNavigation(true);
        idView = R.layout.fragment_user_sign_up;
        testController = Controllers.getInstance().getTestController();
    }

    @Override
    protected void actionsOnCreateView() {
        super.actionsOnCreateView();
        setTitle(R.string.signup_title);
    }

    @Override
    protected void actionsOnActivityCreated() {
        super.actionsOnActivityCreated();
        final List<KeyValuePair> values = new ArrayList<>();
        final List<KeyValuePair> values2 = new ArrayList<>();
        final List<KeyValuePair> values3 = new ArrayList<>();
        testController.readAll(new _RestService.ActionService<TestEntity>() {
            @Override
            public void run(List<TestEntity> entities) {
                super.run(entities);
                try {
                    values.addAll(Tools.objToKeyValuePair(entities, TestEntity.FIELD_KEY, TestEntity.FIELD_TEXTO));
                    values2.addAll(Tools.objToKeyValuePair(entities, TestEntity.FIELD_KEY, TestEntity.FIELD_TEXTO));
                    values3.addAll(Tools.objToKeyValuePair(entities, TestEntity.FIELD_KEY, TestEntity.FIELD_TEXTO));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
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
    protected void setFabIconFunction() {
        super.setFabIconFunction();
        Tools.getFab().hide();
    }
}
