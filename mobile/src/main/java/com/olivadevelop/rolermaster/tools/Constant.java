package com.olivadevelop.rolermaster.tools;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 01/02/2018.
 * RolerMaster
 */
public abstract class Constant {
    public static final boolean EXPORT_PDF_FULL = true;
    public static final boolean EXPORT_PDF_PARTIAL = false;

    public static final int REQUEST_CODE = 1;

    public static final int INFO_CODE = 1;
    public static final int WARN_CODE = 2;
    public static final int ERRO_CODE = 3;

    public static final int MINIMUM_AGE_COMPREHENSION = 8;

    public static final int YEAR_MIN = 1900;
    public static final int TIME_SPLASH = 2000; // Miliseconds

    /*public static final String SERVER = "10.0.3.2";*/     //Genymotion
    public static final String SERVER = "172.16.4.73";      // Physical device
    public static final String PORT = ":80";                // Physical device
    public static final String HOSTNAME = "http://" + SERVER + PORT + "/rolermaster/";
    public static final String SERVICE_URL = HOSTNAME + "www/php/";

    // Android app dirs structure
    public static final String CLIENT_DIR = "clients";
    public static final String IMAGE_DIR = "img";

    public static final String CRYPT_KEY = "rolermasterolivadevelop";
    /*public static final String EXTERNAL_DIR = Environment.getExternalStorageDirectory() + "/RolerMasterPictures/";*/

    public static boolean FLAG_ACTIVE = true;
    public static boolean FLAG_INACTIVE = false;
}
