package com.olivadevelop.rolermaster.activities.fragments;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;

public class SettingsFragment extends CustomFragment {

    public SettingsFragment() {
        idView = R.layout.fragment_settings;
    }

    @Override
    protected void actionsOnCreateView() {
        super.actionsOnCreateView();
        setTitle(R.string.nav_tools);
    }

    @Override
    protected void setFabIconFunction() {
        super.setFabIconFunction();
        Tools.getFab().hide();
    }
}
