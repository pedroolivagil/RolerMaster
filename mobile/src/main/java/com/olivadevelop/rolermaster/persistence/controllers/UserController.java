package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.old.TestEntity;
import com.olivadevelop.rolermaster.persistence.entities.old.User;
import com.olivadevelop.rolermaster.persistence.managers._RestService;

import java.util.List;

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
    public User read(Integer idEntity, _RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public User read(String codeEntity, _RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public List<User> readAll(_RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public List<User> readByIds(List<Integer> ids, _RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public List<User> readAllActives(_RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public List<User> readAllActivesByIds(_RestService.ActionService<TestEntity> actionService) {
        return null;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }
}
