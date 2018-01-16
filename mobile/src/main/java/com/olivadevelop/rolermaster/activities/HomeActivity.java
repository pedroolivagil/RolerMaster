package com.olivadevelop.rolermaster.activities;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.persistence.entities.User;
import com.olivadevelop.rolermaster.tools.AdsAdMob;
import com.olivadevelop.rolermaster.tools.Navigation;
import com.olivadevelop.rolermaster.tools.SessionManager;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Preferences;
import com.olivadevelop.rolermaster.tools.utils.RolerMasterActivity;

import java.util.concurrent.Callable;

import static com.olivadevelop.rolermaster.tools.Tools.Logger;
import static com.olivadevelop.rolermaster.tools.Tools.TIME_TO_EXIT;
import static com.olivadevelop.rolermaster.tools.Tools.isNotNull;
import static com.olivadevelop.rolermaster.tools.Tools.isNull;
import static com.olivadevelop.rolermaster.tools.Tools.newBooleanDialog;
import static com.olivadevelop.rolermaster.tools.Tools.newInfoDialog;

public class HomeActivity extends RolerMasterActivity {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    public HomeActivity() {
        layout = R.layout.activity_main;
    }

    @Override
    protected void initialize() {
        super.initialize();
        // Inicializamos variables
        Navigation.getInstance().setFragmentManager(getSupportFragmentManager());
        // Inicializamos el menú lateral izquierdo
        setToolbar();
        setNavigationDrawer();
        // Inicializamos el boón flotante
        setMainScrollView();
        setModalView();
        setFloatingActionButton();
        setBasicUserData();
        Navigation.getInstance().navigate(Navigation.Page.BLANK_FRAGMENT);
    }

    @Override
    public void onBackPressed() {
        if (getDrawer().isDrawerOpen(GravityCompat.START)) {
            getDrawer().closeDrawer(GravityCompat.START);
        } else {
            if (Navigation.getInstance().hasPages() && !Navigation.getInstance().isFirstPage()) {
                Navigation.getInstance().back();
            } else {
                navBtnExit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_games) {
        } else if (id == R.id.nav_trash) {
        } else if (id == R.id.nav_manage) {
            Navigation.getInstance().navigate(Navigation.Page.SETTINGS_FRAGMENT);
        } else if (id == R.id.nav_logout) {
            navBtnLogout();
        } else if (id == R.id.nav_login) {
            Navigation.getInstance().navigate(Navigation.Page.USER_LOGIN_FRAGMENT);
        } else if (id == R.id.nav_exit) {
            navBtnExit();
        }
        getDrawer().closeDrawer(GravityCompat.START);
        return true;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public DrawerLayout getDrawer() {
        if (drawer == null) {
            drawer = findViewById(R.id.drawer_layout);
        }
        return drawer;
    }

    private int getActionBarHeight() {
        int actionBarHeight = 0;
        if (isNotNull(getSupportActionBar())) {
            actionBarHeight = getSupportActionBar().getHeight();
            if (isNull(actionBarHeight) || actionBarHeight == 0) {
                TypedValue tv = new TypedValue();
                if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                    actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
                }
            }
        }
        return actionBarHeight;
    }

    private void setModalView() {
        Tools.setModalView((LinearLayout) findViewById(R.id.modalView));
        Tools.getModalView().setPadding(0, getActionBarHeight(), 0, 0);
    }

    private void setMainScrollView() {
        Tools.setMainScrollView((ScrollView) findViewById(R.id.mainScrollView));
        Tools.getMainScrollView().setPadding(0, getActionBarHeight(), 0, 0);
    }

    private void setFloatingActionButton() {
        Tools.setFab((FloatingActionButton) findViewById(R.id.fab));
    }

    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, getDrawer(), getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getDrawer().addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        cleanNavigationDrawer();
        if (SessionManager.getInstance().isLogged()) {
            navigationView.inflateHeaderView(R.layout.nav_header_main_login);
            navigationView.inflateMenu(R.menu.activity_main_drawer_login);
            setBasicUserData();
        } else {
            navigationView.inflateHeaderView(R.layout.nav_header_main_logout);
            navigationView.inflateMenu(R.menu.activity_main_drawer_logout);
        }
    }

    private void cleanNavigationDrawer() {
        View headerView = navigationView.getHeaderView(0);
        Menu menu = navigationView.getMenu();
        if (Tools.isNotNull(headerView)) {
            navigationView.removeHeaderView(headerView);
        }
        if (Tools.isNotNull(menu)) {
            menu.clear();
        }
    }

    private void setBasicUserData() {
        if (SessionManager.getInstance().isLogged() && navigationView != null) {
            View headerLayout = navigationView.getHeaderView(0);
            ImageView navHeaderImg = headerLayout.findViewById(R.id.nav_header_image);
            TextView navUserName = headerLayout.findViewById(R.id.nav_user_name);
            TextView navUserMail = headerLayout.findViewById(R.id.nav_user_email);

            User user = Controllers.getInstance().getUserController().find(Preferences.getPrefs().getInt(Preferences.EnumBundle.SESSION_ID_USER, 0));
            if (Tools.isNotNull(user)) {
                navUserName.setText(Tools.capitalize(user.getUsername()));
            }
            if (Tools.isNotNull(user)) {
                navUserMail.setText(user.getEmail());
            }
        }
    }

    private void navBtnLogout() {
        final Context c = this;
        newBooleanDialog(this, R.string.nav_dialog_logout_title, R.string.nav_dialog_sure_message, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                SessionManager.getInstance().logout();
                /*Navigation.getInstance().navigateActivityThread(Navigation.Page.HOME_ACTIVITY, c, 0, null);*/
                Navigation.getInstance().navigate();
                setNavigationDrawer();
                return null;
            }
        });
    }

    private void navBtnExit() {
        newBooleanDialog(this, R.string.nav_dialog_exit_title, R.string.nav_dialog_sure_message, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                exitStep1();
                return null;
            }
        });
    }

    private void exitStep1() {
        newInfoDialog(this, R.string.nav_dialog_exit_2_title, R.string.nav_dialog_exit_2_message, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                exitStep2();
                return null;
            }
        });
        new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < TIME_TO_EXIT) {
                        sleep(100);
                        waited += 100;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            exitStep2();
                        }
                    });
                }
            }
        }.start();
    }

    private void exitStep2() {
        Logger(this, ".exitStep2 --> Saliendo.... (MOCK)");
        AdsAdMob.getInstance().printIntersicial();
    }
}
