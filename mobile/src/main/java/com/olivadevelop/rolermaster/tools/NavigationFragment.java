package com.olivadevelop.rolermaster.tools;

import com.olivadevelop.rolermaster.activities.HomeActivity;
import com.olivadevelop.rolermaster.activities.SplashActivity;
import com.olivadevelop.rolermaster.activities.fragments.BlankFragment;
import com.olivadevelop.rolermaster.activities.fragments.SettingsFragment;
import com.olivadevelop.rolermaster.activities.fragments.UserForgotPassFragment;
import com.olivadevelop.rolermaster.activities.fragments.UserForgotPassStep1Fragment;
import com.olivadevelop.rolermaster.activities.fragments.UserForgotPassStep2Fragment;
import com.olivadevelop.rolermaster.activities.fragments.UserForgotPassStep3Fragment;
import com.olivadevelop.rolermaster.activities.fragments.UserLoginFragment;
import com.olivadevelop.rolermaster.activities.fragments.UserSignUpFragment;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 08/01/2018.
 * RolerMaster
 */
public abstract class NavigationFragment {

    // Activities
    public static final Class HOME_ACTIVITY = HomeActivity.class;
    public static final Class SPLASH_ACTIVITY = SplashActivity.class;

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
