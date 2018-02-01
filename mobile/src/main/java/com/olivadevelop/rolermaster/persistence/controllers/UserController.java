package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.User;
import com.olivadevelop.rolermaster.persistence.managers._RestService;
import com.olivadevelop.rolermaster.tools.Constant;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.Alert;
import com.olivadevelop.rolermaster.tools.utils.KeyValuePair;
import com.olivadevelop.rolermaster.tools.utils.QueryBuilder;
import com.olivadevelop.rolermaster.tools.utils.intefraces.ControllerMethods;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class UserController extends _BasicController<User> implements ControllerMethods<User> {

    UserController() {
        super(User.class);
    }

    @Override
    public User read(Integer idEntity, _RestService.ActionService<User> actionService) {
        User retorno = null;
        try {
            List<KeyValuePair> values = new ArrayList<>();
            values.add(new KeyValuePair("idUser", idEntity));
            FormBody query = getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.find(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "User not found", null);
        }
        return retorno;
    }

    @Override
    public User read(String usermail, _RestService.ActionService<User> actionService) {
        User retorno = null;
        try {
            List<KeyValuePair> values = new ArrayList<>();
            values.add(new KeyValuePair("username", usermail));
            values.add(new KeyValuePair("email", usermail));
            FormBody query = getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.find(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "User not found", null);
        }
        return retorno;
    }

    @Override
    public List<User> readAll(_RestService.ActionService<List<User>> actionService) {
        List<User> retorno = null;
        try {
            retorno = super.findAll();
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Users not found", null);
        }
        return retorno;
    }

    @Override
    public List<User> readByIds(List<Integer> ids, _RestService.ActionService<List<User>> actionService) {
        List<User> retorno = null;
        try {
            List<KeyValuePair> values = new ArrayList<>();
            values.add(new KeyValuePair("idUser", ids));
            FormBody query = getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.findAll(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Users not found", null);
        }
        return retorno;
    }

    @Override
    public List<User> readAllActives(_RestService.ActionService<List<User>> actionService) {
        List<User> retorno = null;
        try {
            List<KeyValuePair> values = new ArrayList<>();
            values.add(new KeyValuePair("flagActive", true));
            FormBody query = getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.findAll(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Users not found", null);
        }
        return retorno;
    }

    @Override
    public List<User> readAllActivesByIds(List<Integer> ids, _RestService.ActionService<List<User>> actionService) {
        List<User> retorno = null;
        try {
            List<KeyValuePair> values = new ArrayList<>();
            values.add(new KeyValuePair("idUser", ids));
            values.add(new KeyValuePair("flagActive", true));
            FormBody query = getQueryBuilder().createQuery(QueryBuilder.TypeQuery.FIND_ONE, values);
            retorno = super.findAll(query);
            if (Tools.isNotNull(actionService)) {
                actionService.run(retorno);
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "Users not found", null);
        }
        return retorno;
    }

    @Override
    public boolean create(User entity) {
        boolean retorno = false;
        try {
            entity.setFlagActive(Constant.FLAG_ACTIVE);
            entity.setFlagStatus(Constant.FLAG_ACTIVE);
            super.persist(entity);
            retorno = true;
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "User con not be inserted", null);
        }
        return retorno;
    }

    @Override
    public boolean update(User entity) {
        boolean retorno = false;
        try {
            super.merge(entity);
            retorno = true;
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "User can not be updated", null);
        }
        return retorno;
    }

    @Override
    public boolean delete(User entity) {
        boolean retorno = false;
        try {
            super.remove(entity);
            retorno = true;
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Alert.getInstance().errorDialog(Tools.Error.ERROR_500, "User can not be deleted", null);
        }
        return retorno;
    }
}
