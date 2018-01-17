package com.olivadevelop.rolermaster.activities.fragments;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.SessionManager;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.layouts.RolerMasterCardView;
import com.olivadevelop.rolermaster.tools.utils.Alert;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

public class BlankFragment extends CustomFragment {

    private RolerMasterCardView rolerMasterCardView;

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
        TextView blankUsername = findViewById(R.id.blank_username);
        LinearLayout mainCardGame = findViewById(R.id.mainCardGame);
        if (SessionManager.getInstance().isLogged()) {
            blankUsername.setText(
                    Preferences.getPrefs().getString(Preferences.EnumBundle.SESSION_USERNAME, getString(R.string.blank_guest))
            );

            TextView blankLastTitle = findViewById(R.id.blankLastTitle);
            TextView blankLastDate = findViewById(R.id.blankLastDate);
            // TODO: MOCK, deberemos recuperar la última partida modificada ACTIVA del usuario. si devuelve algún resultado, creamos una RolerCardView y la mostramos
            rolerMasterCardView = new RolerMasterCardView(this.getContext());
            rolerMasterCardView.setActive(true);
            rolerMasterCardView.setTitle_card_view(getString(R.string.lorem_small));
            rolerMasterCardView.setDescript_card_view(getString(R.string.lorem_large));
            rolerMasterCardView.setId_card_view(1);
            rolerMasterCardView.setImage_card_view("https://cdn1.iconfinder.com/data/icons/google_jfk_icons_by_carlosjj/256/chrome.png");
            rolerMasterCardView.setOnClickListener(this);

            if (rolerMasterCardView.isActive()) {
                blankLastTitle.setVisibility(View.VISIBLE);
                blankLastDate.setVisibility(View.VISIBLE);
                blankLastDate.setText(Tools.getCurrentDate());
                mainCardGame.addView(rolerMasterCardView);
            }
        } else {
            blankUsername.setText(getString(R.string.blank_guest));
        }
    }

    @Override
    protected void actionsOnClick(View v) {
        super.actionsOnClick(v);
        if (v == rolerMasterCardView) {
            Alert.ActionAlert action = new Alert.ActionAlert() {
                @Override
                public void run() {
                    super.run();
                    Log.e("Test ActionAlert", "clicked");
                }
            };
        }
    }

    @Override
    protected void setFabIconFunction() {
        if (SessionManager.getInstance().isLogged()) {
            Tools.getFab().setImageResource(R.drawable.book_plus);
        } else {
            Tools.getFab().setImageResource(R.drawable.account_key_white);
        }
        Tools.getFab().setOnClickListener(this);
        Tools.getFab().show();
    }

    @Override
    protected void actionsFab(View v) {
        super.actionsFab(v);
        if (SessionManager.getInstance().isLogged()) {
            Tools.LoggerSnack(v, this, "Replace with an action");
        } else {
            Navigation.getInstance().navigate(Navigation.Page.USER_LOGIN_FRAGMENT);
        }
    }
}
