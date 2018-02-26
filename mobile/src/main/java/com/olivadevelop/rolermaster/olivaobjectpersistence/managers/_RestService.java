package com.olivadevelop.rolermaster.olivaobjectpersistence.managers;

import android.os.AsyncTask;

import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Alert;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.OlivaDevelopThread;
import com.olivadevelop.rolermaster.tools.Constant;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
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

final class _RestService extends AsyncTask<RequestBody, Void, JSONObject> {

    private String url;

    _RestService(String relativeUrl) {
        this.url = relativeUrl;
    }

    @Override
    protected JSONObject doInBackground(RequestBody... formbody) {
        JSONObject retorno = null;
        try {
            URL url = new URL(Constant.SERVICE_URL + this.url);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formbody[0])
                    .build();
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if (Tools.isNotNull(body)) {
                String result = body.string();
                retorno = new JSONObject(result);
            }
            response.close();
        } catch (final SocketTimeoutException e) {
            OlivaDevelopThread.getInstance().newThread(0, new OlivaDevelopThread.ActionThread() {
                @Override
                public void run() {
                    Alert.getInstance().hideLoadingDialog();
                    Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "TimeOutException.\n\n" + e.getMessage(), null);
                }
            });
        } catch (final ConnectException e) {
            e.printStackTrace();
            Alert.getInstance().getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Alert.getInstance().hideLoadingDialog();
                    Alert.getInstance().errorDialog(Tools.Error.ERROR_400, "ConnectException.\n\n" + e.getMessage(), null);
                }
            });
        } catch (final Exception e) {
            e.printStackTrace();
            Alert.getInstance().getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Alert.getInstance().hideLoadingDialog();
                    Alert.getInstance().errorDialog(Tools.Error.ERROR_400, "GenericException.\n\n" + e.getMessage(), null);
                }
            });
        }
        return retorno;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        super.onPostExecute(json);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        OlivaDevelopThread.getInstance().newThread(new OlivaDevelopThread.ActionThread() {
            @Override
            public void run() {

            }
        });
    }

}
