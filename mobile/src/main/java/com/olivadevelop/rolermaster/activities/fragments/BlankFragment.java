package com.olivadevelop.rolermaster.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;

public class BlankFragment extends CustomFragment {

    public BlankFragment() {
        // Required empty public constructor
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
