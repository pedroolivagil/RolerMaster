package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.Entity;
import com.olivadevelop.rolermaster.persistence.entities.User;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class UserController extends _BasicController<User> {

    @Override
    public User find(Integer idEntity) {
        User user = new User();
        user.setUsername("Test User");
        user.setEmail("qqweert@kjdf.fer");
        user.setPassword("1234");
        return user;
    }

    @Override
    public User findOneByEntity(Entity entity) {
        User user = new User();
        user.setUsername("Test User");
        user.setEmail("qqweert@kjdf.fer");
        user.setPassword("1234");
        return user;
    }

    public User find(String usernamEmail) {
        User userQuery = new User();
        userQuery.setEmail(usernamEmail);
        userQuery.setUsername(usernamEmail);
        return findOneByEntity(userQuery);
    }

}
