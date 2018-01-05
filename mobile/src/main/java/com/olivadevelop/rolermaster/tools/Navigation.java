package com.olivadevelop.rolermaster.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.activities.BlankFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private List<KeyValuePairClass> fragments;

    public static Navigation getInstance() {
        return ourInstance;
    }

    private Navigation() {
        fragments = new ArrayList<>();
        navHomeFirst = true;
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
        try {
            if (lastNotMatch(fragmentClass)) {
                CustomFragment fragment = customTransaction(fragmentClass, true);
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

    public void back() {
        try {
            if (!hasPages()) {
                // si no tiene páginas, creamos la de inicio
                fragments.add(new KeyValuePairClass(BlankFragment.class, true));
            } else {
                // si tiene páginas, comprobamos que la última es navegable. Si no lo es la removemosy ejecutamos de nuevo la funcion hasta que haya una navegable.
                int lastPosition = fragments.size() - 1;
                KeyValuePairClass fragment = fragments.get(lastPosition);
                if (fragment.isInclude()) {
                    // Si el fragment es de los que no se incluyen, lo borramos y ejecutamos el método de nuevo
                    fragments.remove(lastPosition);
                    navIgnored = true;
                    back();
                } else {
                    if (navIgnored) {
                        // si se han ignorado fragments, bastará con navegar hasta el último fragment no ignorado
                        /*fragments.remove(lastPosition);*/
                        navIgnored = false;
                        backNavigate(fragment);
                    } else if (fragment.equals(currentNavigationFragment)) {
                        // en caso de que no se hayan ignorado fragments, si el último fragment es igual al actual, lo borramos
                        fragments.remove(lastPosition);
                        back();
                    } else {
                        // Después de que se haya actualizado el arbol de fragments, navegamos.
                        backNavigate(fragment);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Navigate -> Error: ", e.getMessage());
        }
    }

    private void backNavigate(KeyValuePairClass fragment) throws InstantiationException, IllegalAccessException {
        Class fragmentClass = fragment.getKey();
        customTransaction(fragmentClass, false);
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
        if (!fragments.isEmpty() && fragments.get(fragments.size() - 1).getKey().equals(fragmentClass)) {
            retorno = false;
        }
        return retorno;
    }

    private CustomFragment customTransaction(Class fragmentClass, boolean leftToRight) throws IllegalAccessException, InstantiationException {
        CustomFragment fragment = (CustomFragment) fragmentClass.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!fragment.isIgnoreEffects() || (!navHomeFirst && fragment.getClass().equals(BlankFragment.class))) {
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

}
