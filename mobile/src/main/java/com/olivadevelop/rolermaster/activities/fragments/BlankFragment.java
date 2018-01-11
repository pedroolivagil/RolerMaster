package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.EnumBundle;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

public class BlankFragment extends CustomFragment {

    private TextView blankUsername;

    public BlankFragment() {
        super();
        setIgnoreEffects(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        setTitle(R.string.index_title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        blankUsername = findViewById(R.id.blank_username);
        blankUsername.setText(
                Preferences.getPrefs().getString(EnumBundle.LOGIN_EMAIL, getString(R.string.blank_guest))
        );
    }

    @Override
    protected void setFabIconFunction() {
        Tools.getFab().setImageResource(R.drawable.book_plus);
        Tools.getFab().setOnClickListener(this);
        Tools.getFab().show();
    }

    @Override
    public void onClick(View v) {
        if (v == Tools.getFab()) {
            Tools.LoggerSnack(v, this, "Replace text");
        }
    }
}
