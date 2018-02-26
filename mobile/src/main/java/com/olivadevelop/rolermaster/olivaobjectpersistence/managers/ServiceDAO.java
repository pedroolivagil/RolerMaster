package com.olivadevelop.rolermaster.olivaobjectpersistence.managers;

import android.util.Log;

import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.ActionOlivaDevelop;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.Service;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Alert;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.ToolsOlivaDevelop;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/02/2018.
 * RolerMaster
 */
public final class ServiceDAO implements Service {

    private static final Service service = new ServiceDAO();
    private JSONObject retorno;

    public static Service getInstance() {
        return service;
    }

    private ServiceDAO() {
    }

    @Override
    public JSONObject newCall(ServiceURL url, FormBody body) throws ExecutionException, InterruptedException {
        retorno = new JSONObject();
        _RestService service = new _RestService(url.getUrl());
        service.execute(body);
        retorno = service.get();
        int operations = 0;
        while ((ToolsOlivaDevelop.isNull(retorno) || retorno.toString().equals("{}")) && operations < 500) {
            Log.e(this.getClass().getSimpleName(), "Obteniendo ID generado...");
            operations++;
        }
        return retorno;
    }

    public static class ActionService<T> implements ActionOlivaDevelop {
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
