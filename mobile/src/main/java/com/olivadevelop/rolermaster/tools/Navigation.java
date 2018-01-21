package com.olivadevelop.rolermaster.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ProgressBar;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.activities.HomeActivity;
import com.olivadevelop.rolermaster.activities.fragments.BlankFragment;
import com.olivadevelop.rolermaster.activities.fragments.SettingsFragment;
import com.olivadevelop.rolermaster.activities.fragments.UserForgotPassFragment;
import com.olivadevelop.rolermaster.activities.fragments.UserForgotPassStep1Fragment;
import com.olivadevelop.rolermaster.activities.fragments.UserForgotPassStep2Fragment;
import com.olivadevelop.rolermaster.activities.fragments.UserForgotPassStep3Fragment;
import com.olivadevelop.rolermaster.activities.fragments.UserLoginFragment;
import com.olivadevelop.rolermaster.activities.fragments.UserSignUpFragment;
import com.olivadevelop.rolermaster.tools.utils.CustomFragment;
import com.olivadevelop.rolermaster.tools.utils.CustomList;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePairClass;
import com.olivadevelop.rolermaster.tools.utils.RolerMasterThread;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 * RolerMaster
 */

public class Navigation {

    private static final Navigation ourInstance = new Navigation();
    private boolean navHomeFirst;
    private boolean navIgnored;
    private FragmentManager fragmentManager;
    private KeyValuePairClass currentNavigationFragment;
    private CustomList<KeyValuePairClass> fragments;
    private Bundle lastArgs;
    private Navigation() {
        fragments = new CustomList<>();
        navHomeFirst = true;
    }

    public static Navigation getInstance() {
        return ourInstance;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void navigate(Context c, Class activity) {
        navigate(c, activity, null);
    }

    public void navigate(Context c, Class activity, HashMap<String, Object> params) {
        Intent intent = new Intent(c, activity);
        if (Tools.isNotNull(params)) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                intent.putExtra(param.getKey(), String.valueOf(param.getValue()));
            }
        }
        c.startActivity(intent);
    }

    public void navigate(Class fragmentClass) {
        navigate(fragmentClass, null);
    }

    public void navigateActivityThread(final Class fragmentClass, final Context c, int time, final ProgressBar progressBar) {
        RolerMasterThread.ActionThread action = new RolerMasterThread.ActionThread() {
            @Override
            public void run() {
                super.run();
                Navigation.getInstance().navigate(c, fragmentClass);
            }
        };
        RolerMasterThread.getInstance().newThread(time, action, progressBar);
    }

    public void navigateFragmentThread(final Class fragmentClass,int time, final ProgressBar progressBar) {
        RolerMasterThread.ActionThread action = new RolerMasterThread.ActionThread() {
            @Override
            public void run() {
                super.run();
                Navigation.getInstance().navigate(fragmentClass);
            }
        };
        RolerMasterThread.getInstance().newThread(time, action, progressBar);
    }

    public void navigate(Class fragmentClass, Bundle args) {
        try {
            if (lastNotMatch(fragmentClass)) {
                CustomFragment fragment = customTransaction(fragmentClass, args, true);
                KeyValuePairClass navFrag = new KeyValuePairClass(fragmentClass, fragment.isIgnoreNavigation());
                if (!fragment.isIgnoreNavigation()) {
                    currentNavigationFragment = navFrag;
                }
                fragments.add(navFrag);
            }
            Log.i("Navigate -> ", "Success");
        } catch (Exception e) {
            Log.i("Navigate -> Error: ", e.getMessage());
        }
    }

    public void navigate() {
        navigate(fragments.last().getClass(), lastArgs);
    }

    public void back() {
        back(lastArgs);
    }

    public void back(Bundle args) {
        try {
            if (!hasPages()) {
                // si no tiene páginas, creamos la de inicio
                fragments.add(new KeyValuePairClass(Page.BLANK_FRAGMENT, true));
            } else {
                // si tiene páginas, comprobamos que la última es navegable. Si no lo es la removemosy ejecutamos de nuevo la funcion hasta que haya una navegable.
                int lastPosition = fragments.lastPosition();
                KeyValuePairClass fragment = fragments.last();
                if (fragment.isIncluded()) {
                    // Si el fragment es de los que no se incluyen, lo borramos y ejecutamos el método de nuevo
                    fragments.remove(lastPosition);
                    navIgnored = true;
                    removeLastDuplicated(fragment);
                    back(args);
                } else {
                    if (navIgnored) {
                        // si se han ignorado fragments, bastará con navegar hasta el último fragment no ignorado
                        /*fragments.remove(lastPosition);*/
                        navIgnored = false;
                        backNavigate(fragment, args);
                    } else if (fragment.equals(currentNavigationFragment)) {
                        // en caso de que no se hayan ignorado fragments, si el último fragment es igual al actual, lo borramos
                        fragments.remove(lastPosition);
                        back(args);
                    } else {
                        // Después de que se haya actualizado el arbol de fragments, navegamos.
                        backNavigate(fragment, args);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Navigate -> Error: ", e.getMessage());
        }
    }

    private KeyValuePairClass removeLastDuplicated(KeyValuePairClass fragment) {
        int position = fragments.lastPosition();
        if (position >= 0 && fragment.equals(currentNavigationFragment)) {
            // en caso de que no se hayan ignorado fragments, si el último fragment es igual al actual, lo borramos
            fragments.remove(position);
            fragment = fragments.last();
        }
        return fragment;
    }

    private void backNavigate(KeyValuePairClass fragment, Bundle args) throws InstantiationException, IllegalAccessException {
        Class fragmentClass = fragment.getKey();
        customTransaction(fragmentClass, args, false);
        currentNavigationFragment = fragment;
        Log.i("Navigate -> ", "Success");
    }

    public boolean hasPages() {
        return !fragments.isEmpty();
    }

    public boolean isFirstPage() {
        return fragments.size() == 1;
    }

    private boolean lastNotMatch(Class fragmentClass) {
        boolean retorno = true;
        if (!fragments.isEmpty() && fragments.last().getKey().equals(fragmentClass)) {
            retorno = false;
        }
        return retorno;
    }

    private CustomFragment customTransaction(Class fragmentClass, Bundle args, boolean leftToRight) throws IllegalAccessException, InstantiationException {
        lastArgs = args;
        CustomFragment fragment = (CustomFragment) fragmentClass.newInstance();
        fragment.setArguments(args);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!fragment.isIgnoreEffects() || (!navHomeFirst && fragment.getClass().equals(Page.BLANK_FRAGMENT))) {
            if (leftToRight) {
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            } else {
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            }
            transaction.addToBackStack(fragment.getTag());
        } else {
            navHomeFirst = false;
        }
        transaction.replace(R.id.content_layout, fragment);
        transaction.commit();
        return fragment;
    }

    public abstract static class Page {

        // Activities
        public static final Class HOME_ACTIVITY = HomeActivity.class;

        // Fragments
        public static final Class BLANK_FRAGMENT = BlankFragment.class;
        public static final Class SETTINGS_FRAGMENT = SettingsFragment.class;
        public static final Class USER_LOGIN_FRAGMENT = UserLoginFragment.class;
        public static final Class USER_SIGN_UP_FRAGMENT = UserSignUpFragment.class;
        public static final Class USER_FORGOT_PASS_FRAGMENT = UserForgotPassFragment.class;
        public static final Class USER_FORGOT_PASS_STEP1_FRAGMENT = UserForgotPassStep1Fragment.class;
        public static final Class USER_FORGOT_PASS_STEP2_FRAGMENT = UserForgotPassStep2Fragment.class;
        public static final Class USER_FORGOT_PASS_STEP3_FRAGMENT = UserForgotPassStep3Fragment.class;
        /*public static final Class _FRAGMENT = .class;*/
    }
}
