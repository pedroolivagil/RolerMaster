package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.Country;
import com.olivadevelop.rolermaster.tools.persistence.controllers._BasicController;
import com.olivadevelop.rolermaster.tools.persistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;
import com.olivadevelop.rolermaster.tools.persistence.interfaces.ControllerMethods;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 14/02/2018.
 */

public class CountryController extends _BasicController<Country> implements ControllerMethods<Country> {

    CountryController() {
        super(Country.class);
    }

    @Override
    public Country read(Integer idEntity, ServiceDAO.ActionService<Country> actionService) {
        return null;
    }

    @Override
    public Country read(String codeEntity, ServiceDAO.ActionService<Country> actionService) {
        return null;
    }

    @Override
    public List<Country> readAll(ServiceDAO.ActionService<List<Country>> actionService) {
        return null;
    }

    @Override
    public List<Country> readByIds(List<Integer> ids, ServiceDAO.ActionService<List<Country>> actionService) {
        return null;
    }

    @Override
    public List<Country> readAllActives(ServiceDAO.ActionService<List<Country>> actionService) {
        return null;
    }

    @Override
    public List<Country> readAllActivesByIds(List<Integer> ids, ServiceDAO.ActionService<List<Country>> actionService) {
        return null;
    }

    @Override
    public boolean create(Country entity) {
        boolean retorno = false;
        try {
            super.persist(entity);
            retorno = true;
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "country con not be inserted", null);
        }
        return retorno;
    }

    @Override
    public boolean update(Country entity) {
        return false;
    }

    @Override
    public boolean delete(Country entity) {
        return false;
    }
}
