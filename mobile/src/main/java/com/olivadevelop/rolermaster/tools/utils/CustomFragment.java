package com.olivadevelop.rolermaster.tools.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.activities.HomeActivity;
import com.olivadevelop.rolermaster.tools.Tools;

import static com.olivadevelop.rolermaster.tools.Tools.isNotNull;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 01/01/2018.
 * RolerMaster
 */

public class CustomFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @LayoutRes
    protected int idView;
    protected OnFragmentInteractionListener mListener;
    protected View view;
    protected Bundle _args;
    private HomeActivity a;
    private boolean ignoreNavigation;
    private boolean ignoreEffects;

    public CustomFragment() {
        setRetainInstance(true);
        idView = R.layout.fragment_blank;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = (HomeActivity) getActivity();
        setFabIconFunction();
        setIgnoreNavigation(false);
        setIgnoreEffects(false);
        _args = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(idView, container, false);
        actionsOnCreateView();
        Tools.hideVirtualKeyboard(this.getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        actionsOnActivityCreated();
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
        actionsOnButtonPressed(uri);
    }

    @Override
    public void onClick(View v) {
        if (v == Tools.getFab()) {
            actionsFab(v);
        } else {
            actionsOnClick(v);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        actionsOnCheckedChanged(compoundButton, b);
    }

    protected void actionsOnCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    protected void actionsOnClick(View v) {

    }

    protected void actionsOnCreateView() {

    }

    protected void actionsOnActivityCreated() {

    }

    protected void actionsFab(View v) {

    }

    protected void actionsOnButtonPressed(Uri uri) {

    }

    public void setTitle(@StringRes int title) {
        setTitle(getString(title));
    }

    public void setTitle(String... titles) {
        if (isNotNull(titles) && titles.length > 0 && isNotNull(a.getSupportActionBar())) {
            StringBuilder retorno = new StringBuilder();
            for (int x = 1; x < titles.length; x++) {
                retorno.append(titles[x]);
                if ((titles.length - 1) > x) {
                    retorno.append(" - ");
                }
            }
            a.getSupportActionBar().setTitle(titles[0]);
            a.getSupportActionBar().setSubtitle(retorno.toString());
        }
    }

    protected <T extends View> T findViewById(@IdRes int id) {
        return getActivity().findViewById(id);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
