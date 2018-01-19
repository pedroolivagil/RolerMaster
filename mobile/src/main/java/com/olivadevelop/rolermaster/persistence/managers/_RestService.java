package com.olivadevelop.rolermaster.persistence.managers;

import android.os.AsyncTask;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;

import org.json.JSONException;
import org.json.JSONObject;

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

// TODO: en caso de no llegar a extender nunca la clase, cambiar a final y eliminar los métodos action* de preExec y postExec
public class _RestService extends AsyncTask<RequestBody, Void, JSONObject> {

    private String url;

    public _RestService(String relativeUrl) {
        this.url = relativeUrl;
    }

    @Override
    protected JSONObject doInBackground(RequestBody... formbody) {
        JSONObject retorno = null; // resultado de la petició, contiene la información
        try {
            URL url = new URL(Tools.SERVICE_URL + this.url);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formbody[0])
                    .build();
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if (Tools.isNotNull(body)) {
                retorno = new JSONObject(body.string());
            }
            response.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_404, "", null);
        }
        return retorno;
    }

    @Override
    protected void onPostExecute(JSONObject t) {
        super.onPostExecute(t);
        Alert.getInstance().hideLoadingDialog();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Alert.getInstance().showLoadingDialog();
    }
}
