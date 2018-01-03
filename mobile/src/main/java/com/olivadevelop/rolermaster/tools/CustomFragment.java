package com.olivadevelop.rolermaster.tools;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Spinner;

import com.olivadevelop.rolermaster.MainActivity;
import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.persistence.entities.TestEntity;

import java.util.List;

/**
 * Created by Oliva on 01/01/2018.
 */

public class CustomFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    public void setTitle(@StringRes int strTitle) {
        MainActivity a = (MainActivity) getActivity();
        a.getSupportActionBar().setTitle(strTitle);
    }

}
