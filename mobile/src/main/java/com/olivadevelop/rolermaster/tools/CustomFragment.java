package com.olivadevelop.rolermaster.tools;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.olivadevelop.rolermaster.MainActivity;

/**
 * Created by Oliva on 01/01/2018.
 */

public class CustomFragment extends Fragment implements View.OnClickListener {

    protected View view;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFabIconFunction();
    }

    public void setTitle(@StringRes int strTitle) {
        MainActivity a = (MainActivity) getActivity();
        a.getSupportActionBar().setTitle(strTitle);
    }


    protected void setFabIconFunction() {

    }

}
