package com.olivadevelop.rolermaster.activities.fragments;

import android.view.View;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

public class BlankFragment extends CustomFragment {

    private TextView blankUsername;

    public BlankFragment() {
        super();
        setIgnoreEffects(true);
    }

    @Override
    protected void actionsOnCreateView() {
        super.actionsOnCreateView();
        setTitle(getString(R.string.app_name), getString(R.string.nav_home));
    }

    @Override
    protected void actionsOnActivityCreated() {
        super.actionsOnActivityCreated();
        blankUsername = findViewById(R.id.blank_username);
        blankUsername.setText(
                Preferences.getPrefs().getString(Preferences.EnumBundle.SESSION_USERNAME, getString(R.string.blank_guest))
        );
    }

    @Override
    protected void setFabIconFunction() {
        Tools.getFab().setImageResource(R.drawable.book_plus);
        Tools.getFab().setOnClickListener(this);
        Tools.getFab().show();
    }

    @Override
    protected void actionsFab(View v) {
        super.actionsFab(v);
        Tools.LoggerSnack(v, this, "Replace text");
    }
}
