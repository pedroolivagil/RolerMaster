package com.olivadevelop.rolermaster.olivaobjectpersistence.managers;

import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.ActionOlivaDevelop;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.Service;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Alert;
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
public class ServiceDAO implements Service {

    private static final Service service = new ServiceDAO();

    public static Service getInstance() {
        return service;
    }

    private ServiceDAO() {
    }

    @Override
    public JSONObject newCall(ServiceURL url, FormBody body) throws ExecutionException, InterruptedException {
        _RestService service = new _RestService(url.getUrl());
        service.execute(body);
        return service.get();
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
