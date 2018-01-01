package com.olivadevelop.rolermaster.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.activities.UserSignUpFragment;

/**
 * Created by Oliva on 01/01/2018.
 */

public class CustomFragment extends Fragment implements View.OnClickListener {

    protected FragmentManager fragmentManager;
    protected UserSignUpFragment.OnFragmentInteractionListener mListener;

    public void init(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
}
