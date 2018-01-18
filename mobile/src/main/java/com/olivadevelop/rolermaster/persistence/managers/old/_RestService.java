package com.olivadevelop.rolermaster.persistence.managers.old;

import android.os.AsyncTask;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 18/01/2018.
 */

public class _RestService extends AsyncTask<RequestBody, Void, String> {

    private String url;

    public _RestService(String url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(RequestBody... formbody) {
        String resStr = null; // resultado de la petició, contiene la información
        try {
            URL url = new URL(this.url);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formbody[0])
                    .build();
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if (Tools.isNotNull(body)) {
                resStr = body.string();
            }
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resStr;
    }

    @Override
    protected void onPostExecute(String t) {
        super.onPostExecute(t);
        Alert.getInstance().hideLoadingDialog();
        actionsPostExecute(t);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Alert.getInstance().showLoadingDialog();
        actionsPreExecute();
    }

    protected void actionsPostExecute(String t) {

    }

    protected void actionsPreExecute() {

    }
}
