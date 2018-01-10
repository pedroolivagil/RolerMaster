package com.olivadevelop.rolermaster.tools;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.olivadevelop.rolermaster.R;

import static com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR;


/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 20/03/2015.
 */
public class AdsAdMob {

    private static final AdsAdMob instance = new AdsAdMob();

    private Context context;
    private InterstitialAd interstitial;

    private AdsAdMob() {

    }

    public static AdsAdMob getInstance() {
        return instance;
    }

    public void initialize(Context c) {
        setContext(c);
    }

    private Context getContext() {
        return context;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    public void loadIntersicial() {
        MobileAds.initialize(getContext(), getContext().getString(R.string.id_publisher_ads));
        // Create the interstitial.
        interstitial = new InterstitialAd(getContext());
        interstitial.setAdUnitId(getContext().getString(R.string.interstitial_ad_unit_id));
        // Create ad request.
        AdRequest adRequest2 = new AdRequest.Builder()
                .addTestDevice(DEVICE_ID_EMULATOR)
                .build();
        // Begin loading your interstitial.
        interstitial.loadAd(adRequest2);
    }

    public void printIntersicial() {
        printIntersicial(true);
    }

    public void printIntersicial(boolean newAds) {
        if (interstitial == null || newAds) {
            loadIntersicial();
        } else {
            if (interstitial.isLoaded()) {
                interstitial.show();
            }
        }
    }

    public void printBanner(AdView mAdView) {
        if (mAdView != null) {
            MobileAds.initialize(getContext(), getContext().getString(R.string.id_publisher_ads));
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }
    }
}
