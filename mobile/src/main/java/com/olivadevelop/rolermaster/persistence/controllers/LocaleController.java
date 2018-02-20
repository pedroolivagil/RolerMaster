package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.Locale;
import com.olivadevelop.rolermaster.olivaobjectpersistence.controllers._BasicController;
import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.ControllerMethods;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 18/02/2018.
 * RolerMaster
 */
public class LocaleController extends _BasicController<Locale> implements ControllerMethods<Locale> {

    LocaleController() {
        super(Locale.class);
    }

    @Override
    public Locale read(Integer idEntity, ServiceDAO.ActionService<Locale> actionService) {
        return null;
    }

    @Override
    public Locale read(String codeEntity, ServiceDAO.ActionService<Locale> actionService) {
        return null;
    }

    @Override
    public List<Locale> readAll(ServiceDAO.ActionService<List<Locale>> actionService) {
        return null;
    }

    @Override
    public List<Locale> readByIds(List<Integer> ids, ServiceDAO.ActionService<List<Locale>> actionService) {
        return null;
    }

    @Override
    public List<Locale> readAllActives(ServiceDAO.ActionService<List<Locale>> actionService) {
        return null;
    }

    @Override
    public List<Locale> readAllActivesByIds(List<Integer> ids, ServiceDAO.ActionService<List<Locale>> actionService) {
        return null;
    }

    @Override
    public boolean create(Locale entity) {
        boolean retorno = false;
        try {
            super.persist(entity);
            retorno = true;
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Locale con not be inserted", null);
        }
        return retorno;
    }

    @Override
    public boolean update(Locale entity) {
        return false;
    }

    @Override
    public boolean delete(Locale entity) {
        return false;
    }
}
