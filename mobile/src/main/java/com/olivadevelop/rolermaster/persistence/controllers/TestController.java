package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.old.TestEntity;
import com.olivadevelop.rolermaster.persistence.managers._RestService;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Clase creada para simular la conexión a la base de datos. En cuanto se implemente la conexión a la bbdd, las clases Test serán borradas
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class TestController extends _BasicController<TestEntity> {

    public TestController() {
        super(TestEntity.class);
    }

    @Override
    public TestEntity find(Integer idEntity, final _RestService.ActionService<TestEntity> actionService) {
        TestEntity retorno = null;
        try {
            retorno = super.find(idEntity, new _RestService.ActionService<TestEntity>() {
                @Override
                public void run(JSONObject json) {
                    try {
                        TestEntity obj = parseJsonToEntity(json, TestEntity.class);
                        actionService.run(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "", null);
        }
        return retorno;
    }

    @Override
    public List<TestEntity> findAll(final _RestService.ActionService<TestEntity> actionService) {
        List<TestEntity> retorno = null;
        try {
            retorno = super.findAll(new _RestService.ActionService<TestEntity>() {
                @Override
                public void run(JSONObject json) {
                    try {
                        List<TestEntity> obj = parseJsonToListEntity(json, TestEntity.class);
                        actionService.run(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "", null);
        }
        return retorno;
    }
}
