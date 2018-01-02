package com.olivadevelop.rolermaster.tools;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Spinner;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.activities.UserSignUpFragment;
import com.olivadevelop.rolermaster.persistence.Entity;

import java.util.List;

/**
 * Created by Oliva on 01/01/2018.
 */

public class CustomFragment extends Fragment implements View.OnClickListener {

    protected FragmentManager fragmentManager;

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

    public void createSpinner(Activity a, View view, int idSpinner, List<Entity> values) {
        Spinner spinner = (Spinner) view.findViewById(idSpinner);
        spinner.setAdapter(new SpinnerAdapter(a, R.layout.custom_spinner, values)); //if you r using fragment
    }
}
