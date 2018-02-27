package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.olivaobjectpersistence.controllers._BasicController;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.ControllerMethods;
import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.Alert;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.KeyValuePair;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.QueryBuilder;
import com.olivadevelop.rolermaster.persistence.entities.Locale;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

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
        Locale retorno = null;
        try {
            List<KeyValuePair<String, ?>> values = new ArrayList<>();
            values.add(new KeyValuePair<>("idLocale", idEntity));
            FormBody query = getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values);
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
    public Locale read(String codeEntity, ServiceDAO.ActionService<Locale> actionService) {
        Locale retorno = null;
        try {
            List<KeyValuePair<String, ?>> values = new ArrayList<>();
            values.add(new KeyValuePair<>("codeISO", codeEntity));
            FormBody query = getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values);
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
            retorno = super.persist(entity);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Locale con not be inserted", null);
        }
        return retorno;
    }

    @Override
    public boolean update(Locale entity) {
        boolean retorno = false;
        try {
            retorno = super.merge(entity);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Locale con not be updated", null);
        }
        return retorno;
    }

    @Override
    public boolean delete(Locale entity) {
        boolean retorno = false;
        try {
            retorno = super.remove(entity);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Locale con not be deleted", null);
        }
        return retorno;
    }
}
