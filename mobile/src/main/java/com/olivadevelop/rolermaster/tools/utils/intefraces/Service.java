package com.olivadevelop.rolermaster.tools.utils.intefraces;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/02/2018.
 * RolerMaster
 */
public interface Service {

    JSONObject newCall(String url, FormBody body) throws ExecutionException, InterruptedException;
}
