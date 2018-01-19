package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.old.Entity;
import com.olivadevelop.rolermaster.persistence.entities.old.User;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class UserController extends _BasicController<User> {

    private User testUser;

    public UserController() {
        super(User.class);
        this.testUser = new User();
        this.testUser.setIdUser(1);
        this.testUser.setUsername("testuser");
        this.testUser.setEmail("qqweert@kjdf.fer");
        this.testUser.setPassword("1234");
    }

    @Override
    public User find(Integer idEntity) {
        return this.testUser;
    }

    @Override
    public User findOneByEntity(Entity entity) {
        return this.testUser;
    }

    public User find(String usernamEmail) {
        User userQuery = new User();
        userQuery.setEmail(usernamEmail);
        userQuery.setUsername(usernamEmail);
        return findOneByEntity(userQuery);
    }

}
