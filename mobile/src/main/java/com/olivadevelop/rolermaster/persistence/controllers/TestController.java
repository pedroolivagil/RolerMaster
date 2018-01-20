package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.old.TestEntity;
import com.olivadevelop.rolermaster.persistence.managers._RestService;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    protected List<TestEntity> parseJsonToListEntity(JSONObject json, Class<TestEntity> entity) throws JSONException {
        List<TestEntity> retorno = new ArrayList<>();
        if (Tools.isNotNull(json)) {
            JSONArray array = json.getJSONArray(entity.getSimpleName());
            if (Tools.isNotNull(array)) {
                for (int x = 0; x < array.length(); x++) {

                }
            }
        }
        return retorno;
    }

    @Override
    protected TestEntity parseJsonToEntity(JSONObject json, Class<TestEntity> entity) throws JSONException {
        /*TestEntity retorno = null;
        if (Tools.isNotNull(json)) {
            JSONArray array = json.getJSONArray(entity.getSimpleName());
            if (Tools.isNotNull(array)) {
                retorno = new TestEntity(array.getJSONObject(0));
            }
        }
        return retorno;*/
        return super.parseJsonToEntity(json, entity);
    }

    public TestEntity testParse(JSONObject json, Class<TestEntity> entity) throws JSONException {
        return parseJsonToEntity(json, entity);
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
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "", null);
        }
        return retorno;
    }

    @Override
    public List<TestEntity> findAll() {
        List<TestEntity> retorno = null;
        try {
            retorno = super.findAll();
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "", null);
        }
        return retorno;
    }
}
