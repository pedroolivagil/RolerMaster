package com.olivadevelop.rolermaster.tools.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Alert;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.OlivaDevelopThread;
import com.olivadevelop.rolermaster.tools.AdsAdMob;
import com.olivadevelop.rolermaster.tools.Navigation;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 10/01/2018.
 */

@SuppressLint("Registered")
public class RolerMasterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 2;

    @LayoutRes
    protected int layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initialize();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initialize();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public final void update() {
        initialize();
    }

    protected void initialize() {
        setContentView(layout);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestPermisions();
            }
        }, 1500);
        Alert.getInstance().setActivity(this);
        Alert.getInstance().setLoadingDialog();
        Navigation.getInstance().setFragmentManager(getSupportFragmentManager());
        OlivaDevelopThread.getInstance().setContext(this);
        AdsAdMob.getInstance().initialize(this);
        Preferences.getInstance().init(this);
    }

    protected void requestPermisions() {
        /*if (!havePermissionCamera()) {
            //no tenemos permisos, los solicitamos
            setPermissionCamera();
        }
        if (!havePermissionStorage()) {
            //no tenemos permisos, los solicitamos
            setPermissionStorage();
        }*/
    }

    /**
     * Permisos: métodos
     */
    protected boolean havePermissionCamera() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    protected boolean havePermissionStorage() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    protected void setPermissionStorage() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestForResultStoragePermission();
        } else {
            requestForResultStoragePermission();
        }
    }

    protected void setPermissionCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            requestForResultCameraPermission();
        } else {
            requestForResultCameraPermission();
        }
    }

    protected void requestForResultStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
    }

    protected void requestForResultCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                MY_PERMISSIONS_REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("PERMISSION STORAGE", "GRANTED");
                } else {
                    Log.e("PERMISSION STORAGE", "GRANTED FAIL");
                }
            }
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("PERMISSION CAMERA", "GRANTED");
                } else {
                    Log.e("PERMISSION CAMERA", "GRANTED FAIL");
                }
            }
        }
    }
}
