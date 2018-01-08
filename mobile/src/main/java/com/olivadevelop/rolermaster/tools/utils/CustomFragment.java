package com.olivadevelop.rolermaster.tools.utils;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.olivadevelop.rolermaster.activities.HomeActivity;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 01/01/2018.
 * RolerMaster
 */

public class CustomFragment extends Fragment implements View.OnClickListener {

    protected View view;

    private boolean ignoreNavigation;
    private boolean ignoreEffects;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFabIconFunction();
        setIgnoreNavigation(false);
        setIgnoreEffects(false);
    }

    public void setTitle(@StringRes int strTitle) {
        HomeActivity a = (HomeActivity) getActivity();
        a.getSupportActionBar().setTitle(strTitle);
    }


    protected void setFabIconFunction() {
    }

    public boolean isIgnoreNavigation() {
        return ignoreNavigation;
    }

    public void setIgnoreNavigation(boolean ignoreNavigation) {
        this.ignoreNavigation = ignoreNavigation;
    }

    public boolean isIgnoreEffects() {
        return ignoreEffects;
    }

    public void setIgnoreEffects(boolean ignoreEffects) {
        this.ignoreEffects = ignoreEffects;
    }
}
