package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.old.TestEntity;
import com.olivadevelop.rolermaster.persistence.managers._RestService;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Clase creada para simular la conexión a la base de datos. En cuanto se implemente la conexión a la bbdd, las clases Test serán borradas
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public class TestController extends _BasicController<TestEntity> implements ControllerMethods<TestEntity> {

    TestController() {
        super(TestEntity.class);
    }

    @Override
    public TestEntity read(Integer idEntity, _RestService.ActionService<TestEntity> actionService) {
        TestEntity retorno = null;
        try {
            retorno = super.find(idEntity);
            actionService.run(retorno);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "", null);
        }
        return retorno;
    }

    @Override
    public TestEntity read(String codeEntity, _RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public List<TestEntity> readAll(_RestService.ActionService<TestEntity> actionService) {
        List<TestEntity> retorno = null;
        try {
            retorno = super.findAll();
            actionService.run(retorno);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "", null);
        }
        return retorno;
    }

    @Override
    public List<TestEntity> readByIds(List<Integer> ids, _RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public List<TestEntity> readAllActives(_RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public List<TestEntity> readAllActivesByIds(_RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public boolean create(TestEntity entity) {
        return false;
    }

    @Override
    public boolean update(TestEntity entity) {
        return false;
    }

    @Override
    public boolean delete(TestEntity entity) {
        return false;
    }
}
