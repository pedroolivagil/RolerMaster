package com.olivadevelop.rolermaster.activities.fragments;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.OlivaDevelopThread;
import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.persistence.entities.Country;
import com.olivadevelop.rolermaster.persistence.entities.CountryTrans;
import com.olivadevelop.rolermaster.persistence.entities.Locale;
import com.olivadevelop.rolermaster.persistence.entities.LocaleTrans;
import com.olivadevelop.rolermaster.persistence.entities.User;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.SessionManager;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.layouts.RolerMasterCardView;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.Preferences;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

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

            try {
                User user = Controllers.getInstance().getUserController().find(1);
                Log.e("USER", user.toString());
            } catch (ExecutionException | InterruptedException | JSONException e) {
                e.printStackTrace();
            }
        } else {
            blankUsername.setText(getString(R.string.blank_guest));
        }

        Locale es = new Locale();
        es.setPersisted(true);
        es.setIdLocale(1);
        es.setCodeIso("ES");

        Locale en = new Locale();
        en.setPersisted(true);
        en.setIdLocale(2);
        en.setCodeIso("EN");

        LocaleTrans trans1 = new LocaleTrans();
        trans1.setPersisted(true);
        trans1.setText("Español");
        trans1.setLocale(es);

        LocaleTrans trans2 = new LocaleTrans();
        trans2.setPersisted(true);
        trans2.setText("Spanish");
        trans2.setLocale(en);

        es.addTranslation(trans1);
        es.addTranslation(trans2);

        Country countryES = new Country();
        countryES.setLocale(es);
        countryES.setCode("ES");

        CountryTrans cEs = new CountryTrans();
        cEs.setCountry(countryES);
        cEs.setText("España");
        cEs.setLocale(es);

        CountryTrans cEn = new CountryTrans();
        cEn.setCountry(countryES);
        cEn.setText("Spain");
        cEn.setLocale(en);

        countryES.addTranslation(cEs);
        countryES.addTranslation(cEn);

        //Controllers.getInstance().getCountryController().create(countryES);
        OlivaDevelopThread.getInstance().newThread(new OlivaDevelopThread.ActionThread() {
            @Override
            public void run() {
                Controllers.getInstance().getCountryController().read(1, new ServiceDAO.ActionService<Country>() {
                    @Override
                    public void run(Country entity) {
                        super.run(entity);
                        Log.e("CountryRead", entity.toString());
                    }
                });
            }
        });
    }

    @Override
    protected void actionsOnClick(View v) {
        super.actionsOnClick(v);
        if (v == rolerMasterCardView) {
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
            final TextView blankUsername = findViewById(R.id.blank_username);
            /*OlivaDevelopThread.getInstance().newThread(new OlivaDevelopThread.ActionThread() {
                @Override
                public void run() {
                    Controllers.getInstance().getTestController().read(1, new _RestService.ActionService<TestEntity>() {
                        @Override
                        public void run(TestEntity entity) {
                            super.run(entity);
                            if (Tools.isNotNull(blankUsername) && Tools.isNotNull(entity)) {
                                blankUsername.setText(entity.getText());
                            }
                        }
                    });
                }
            });*/
            /*OlivaDevelopThread.getInstance().newThread(new OlivaDevelopThread.ActionThread() {
                @Override
                public void run() {
                    Controllers.getInstance().getTestController().readAll(new _RestService.ActionService<TestEntity>() {
                        @Override
                        public void run(List<TestEntity> entities) {
                            super.run(entities);
                            if (Tools.isNotNull(blankUsername) && Tools.isNotNull(entities)) {
                                StringBuilder textos = new StringBuilder();
                                for (TestEntity entity : entities) {
                                    textos.append(entity.getText());
                                }
                                blankUsername.setText(textos.toString());
                            }
                        }
                    });
                }
            });*/
        } else {
            Navigation.getInstance().navigate(Navigation.Page.USER_LOGIN_FRAGMENT);
        }
    }
}
