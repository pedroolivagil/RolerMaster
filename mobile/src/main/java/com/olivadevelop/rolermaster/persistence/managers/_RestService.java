package com.olivadevelop.rolermaster.persistence.managers;

import android.os.AsyncTask;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;
import com.olivadevelop.rolermaster.tools.utils.RolerMasterThread;
import com.olivadevelop.rolermaster.tools.utils.intefraces.ActionRolerMaster;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;

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

    private ActionService actionService;
    private String url;

    public _RestService(String relativeUrl) {
        this(relativeUrl, null);
    }

    public _RestService(String relativeUrl, ActionService action) {
        this.url = relativeUrl;
        this.actionService = action;
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
                String result = body.string();
                retorno = new JSONObject(result);
            }
            response.close();
        } catch (SocketTimeoutException e) {
            RolerMasterThread.getInstance().newThread(0, new RolerMasterThread.ActionThread() {
                @Override
                public void run() {
                    Alert.getInstance().hideLoadingDialog();
                    Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "TimeOutException", null);
                }
            });
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Alert.getInstance().hideLoadingDialog();
                    Alert.getInstance().errorDialog(Tools.Error.ERROR_400, "ServiceException", null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Alert.getInstance().getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Alert.getInstance().hideLoadingDialog();
                    Alert.getInstance().errorDialog(Tools.Error.ERROR_400, "GenericException", null);
                }
            });
        }
        return retorno;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        super.onPostExecute(json);
       /* if (Tools.isNotNull(json) && Tools.isNotNull(actionService)) {
            actionService.run(json);
        }*/
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        RolerMasterThread.getInstance().newThread(new RolerMasterThread.ActionThread(){
            @Override
            public void run() {

            }
        });
    }

    public static class ActionService<T> implements ActionRolerMaster {
        public void run(T entity) {
            if (Tools.isNotNull(Alert.getInstance().getLoadingDialog()) && Alert.getInstance().getLoadingDialog().isShowing()) {
                Alert.getInstance().hideLoadingDialog();
            }
        }

        public void run(List<T> entities) {
            if (Tools.isNotNull(Alert.getInstance().getLoadingDialog()) && Alert.getInstance().getLoadingDialog().isShowing()) {
                Alert.getInstance().hideLoadingDialog();
            }
        }

        public void run(JSONObject json) {

        }

        @Override
        public void run() {
        }
    }
}
