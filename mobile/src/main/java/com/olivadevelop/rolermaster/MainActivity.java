package com.olivadevelop.rolermaster;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.olivadevelop.rolermaster.activities.UserLoginFragment;
import com.olivadevelop.rolermaster.tools.AdsAdMob;
import com.olivadevelop.rolermaster.tools.SessionManager;
import com.olivadevelop.rolermaster.tools.Tools;

import java.util.concurrent.Callable;

import static com.olivadevelop.rolermaster.tools.Tools.TIME_TO_EXIT;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private static FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inicilizamos la publicidad (moverlo al splashScreen
        AdsAdMob.getInstance().initialize(this);
        SessionManager.getInstance().setLogged(false);

        // Inicializamos el menú lateral izquierdo
        navMenuLeft();
        // Inicializamos el boón flotante
        floatingActionButton();

        setBasicUserData();
    }

    @Override
    public void onBackPressed() {
        if (getDrawer().isDrawerOpen(GravityCompat.START)) {
            getDrawer().closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_games) {
        } else if (id == R.id.nav_trash) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_logout) {
        } else if (id == R.id.nav_login) {
            Tools.navigateFragment(getSupportFragmentManager(), UserLoginFragment.class);
        } else if (id == R.id.nav_exit) {
            navBtnExit();
        }
        getDrawer().closeDrawer(GravityCompat.START);
        return true;
    }

    public DrawerLayout getDrawer() {
        if (drawer == null) {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        }
        return drawer;
    }

    public static FloatingActionButton getFab() {
        return fab;
    }

    private void floatingActionButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void navMenuLeft() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, getDrawer(), toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getDrawer().addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (SessionManager.getInstance().isLogged()) {
            navigationView.inflateHeaderView(R.layout.nav_header_main_login);
            navigationView.inflateMenu(R.menu.activity_main_drawer_login);
        } else {
            navigationView.inflateHeaderView(R.layout.nav_header_main_logout);
            navigationView.inflateMenu(R.menu.activity_main_drawer_logout);
        }
        /*AdsAdMob.getInstance().printBanner((AdView) findViewById(R.id.navAdView));*/
    }

    private void setBasicUserData() {
        if (SessionManager.getInstance().isLogged() && navigationView != null) {
            /*View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main_login);*/
            View headerLayout = navigationView.getHeaderView(0);
            ImageView navHeaderImg = (ImageView) headerLayout.findViewById(R.id.nav_header_image);
            TextView navUserName = (TextView) headerLayout.findViewById(R.id.nav_user_name);
            TextView navUserMail = (TextView) headerLayout.findViewById(R.id.nav_user_email);

            // Añadimos datos falsos para el test
            navUserName.setText("Test Name User");
            navUserMail.setText("usermail@test.com");
        }
    }

    private void navBtnExit() {
        Tools.newBooleanDialog(this, R.string.nav_dialog_exit_title, R.string.nav_dialog_exit_message, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                exitStep1();
                return null;
            }
        });
    }

    private void exitStep1() {
        Tools.newInfoDialog(this, R.string.nav_dialog_exit_2_title, R.string.nav_dialog_exit_2_message, new Callable<Void>() {
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
        Tools.Logger(this, ".exitStep2 --> Saliendo.... (MOCK)");
        AdsAdMob.getInstance().printIntersicial();
    }
}
