package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.olivaobjectpersistence.controllers._BasicController;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.ControllerMethods;
import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Alert;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.KeyValuePair;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.QueryBuilder;
import com.olivadevelop.rolermaster.persistence.entities.Country;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

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
        Country retorno = null;
        try {
            List<KeyValuePair<String, ?>> values = new ArrayList<>();
            values.add(new KeyValuePair<>("idCountry", idEntity));
            FormBody query = getQueryBuilder().query(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.find(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country not found" + e.getMessage(), null);
        }
        return retorno;
    }

    @Override
    public Country read(String codeEntity, ServiceDAO.ActionService<Country> actionService) {
        Country retorno = null;
        try {
            List<KeyValuePair<String, ?>> values = new ArrayList<>();
            values.add(new KeyValuePair<>(FILTER, codeEntity));
            values.add(new KeyValuePair<>("code", codeEntity));
            FormBody query = getQueryBuilder().query(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.find(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country not found", null);
        }
        return retorno;
    }

    @Override
    public List<Country> readAll(ServiceDAO.ActionService<List<Country>> actionService) {
        List<Country> retorno = null;
        try {
            retorno = super.findAll();
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country not found", null);
        }
        return retorno;
    }

    @Override
    public List<Country> readByIds(List<Integer> ids, ServiceDAO.ActionService<List<Country>> actionService) {
        List<Country> retorno = null;
        try {
            List<KeyValuePair<String, ?>> values = new ArrayList<>();
            values.add(new KeyValuePair<>("idCountry", ids));
            FormBody query = getQueryBuilder().query(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.findAll(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country not found", null);
        }
        return retorno;
    }

    @Override
    public List<Country> readAllActives(ServiceDAO.ActionService<List<Country>> actionService) {
        List<Country> retorno = null;
        try {
            List<KeyValuePair<String, ?>> values = new ArrayList<>();
            values.add(new KeyValuePair<>("flagActive", true));
            FormBody query = getQueryBuilder().query(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.findAll(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country not found", null);
        }
        return retorno;
    }

    @Override
    public List<Country> readAllActivesByIds(List<Integer> ids, ServiceDAO.ActionService<List<Country>> actionService) {
        List<Country> retorno = null;
        try {
            List<KeyValuePair<String, ?>> values = new ArrayList<>();
            values.add(new KeyValuePair<>("idCountry", ids));
            values.add(new KeyValuePair<>("flagActive", true));
            FormBody query = getQueryBuilder().query(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.findAll(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country not found", null);
        }
        return retorno;
    }

    @Override
    public boolean create(Country entity) {
        boolean retorno = false;
        try {
            retorno = super.persist(entity);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country con not be inserted", null);
        }
        return retorno;
    }

    @Override
    public boolean update(Country entity) {
        boolean retorno = false;
        try {
            retorno = super.merge(entity);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country con not be updated", null);
        }
        return retorno;
    }

    @Override
    public boolean delete(Country entity) {
        boolean retorno = false;
        try {
            retorno = super.remove(entity);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Country con not be deleted", null);
        }
        return retorno;
    }
}
