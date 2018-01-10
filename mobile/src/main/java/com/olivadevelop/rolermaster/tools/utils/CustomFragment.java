package com.olivadevelop.rolermaster.tools.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.activities.HomeActivity;

import static com.olivadevelop.rolermaster.tools.Tools.isNotNull;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 01/01/2018.
 * RolerMaster
 */

public class CustomFragment extends Fragment implements View.OnClickListener {

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
    protected OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    protected View view;
    private HomeActivity a;

    private boolean ignoreNavigation;
    private boolean ignoreEffects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = (HomeActivity) getActivity();
        setFabIconFunction();
        setIgnoreNavigation(false);
        setIgnoreEffects(false);
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
    }

    @Override
    public void onClick(View v) {

    }

    public void setTitle(@StringRes int... titles) {
        if (isNotNull(titles) && titles.length > 0 && isNotNull(a.getSupportActionBar())) {
            StringBuilder retorno = new StringBuilder();
            for (int x = 1; x < titles.length; x++) {
                retorno.append(a.getString(titles[x]));
                if ((titles.length - 1) > x) {
                    retorno.append(" - ");
                }
            }
            a.getSupportActionBar().setTitle(titles[0]);
            a.getSupportActionBar().setSubtitle(retorno.toString());
        }
    }

    protected <T extends View> T findViewById(@IdRes int id){
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
}
