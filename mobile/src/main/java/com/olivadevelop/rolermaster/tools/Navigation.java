package com.olivadevelop.rolermaster.tools;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.olivadevelop.rolermaster.R;
import com.olivadevelop.rolermaster.activities.BlankFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 * RolerMaster
 */

public class Navigation {
    private static final Navigation ourInstance = new Navigation();

    private FragmentManager fragmentManager;
    private List<Class> fragments;

    public static Navigation getInstance() {
        return ourInstance;
    }

    private Navigation() {
        fragments = new ArrayList<>();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void navigate(Class fragmentClass) {
        try {
            CustomFragment fragment = (CustomFragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.content_layout, fragment).commit();
            fragments.add(fragmentClass);
        } catch (Exception e) {
            Log.e("Error Navigate -> ", e.getMessage());
        }
    }

    public void back() {
        try {
            int position = fragments.size() - 1;
            if (hasPages()) {
                fragments.remove(position);
                position--;
            }
            if (position < 0) {
                position = 0;
                fragments.add(BlankFragment.class);
            }
            Class fragmentClass = fragments.get(position);
            CustomFragment fragment = (CustomFragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.content_layout, fragment).commit();
        } catch (Exception e) {
            Log.e("Error Navigate -> ", e.getMessage());
        }
    }

    public boolean hasPages() {
        return !fragments.isEmpty();
    }

    public boolean isFirstPage() {
        return fragments.size() == 1;
    }

}
