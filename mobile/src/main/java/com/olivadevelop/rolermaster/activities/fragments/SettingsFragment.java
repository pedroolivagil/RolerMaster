package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.layouts.BooleanSettingsView;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;

public class SettingsFragment extends CustomFragment {

    private BooleanSettingsView optionTest;

    public SettingsFragment() {
        idView = R.layout.fragment_settings;
    }

    @Override
    protected void actionsOnCreateView() {
        super.actionsOnCreateView();
        setTitle(R.string.nav_tools);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        optionTest = findViewById(R.id.optionTest);
        optionTest.getSwValue().setChecked(true);
        optionTest.getSwValue().setOnCheckedChangeListener(this);
    }

    @Override
    protected void actionsOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        super.actionsOnCheckedChanged(compoundButton, b);
        Tools.LoggerSnack(view, this.getActivity(), "R: " + b);
    }

    @Override
    protected void setFabIconFunction() {
        super.setFabIconFunction();
        Tools.getFab().hide();
    }

}
