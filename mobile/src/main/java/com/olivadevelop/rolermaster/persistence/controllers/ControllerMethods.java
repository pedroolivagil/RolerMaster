package com.olivadevelop.rolermaster.persistence.controllers;

import com.olivadevelop.rolermaster.persistence.entities.old.TestEntity;
import com.olivadevelop.rolermaster.persistence.managers._RestService;

import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 22/01/2018.
 */

public interface ControllerMethods<T> {

    T read(Integer idEntity, _RestService.ActionService<TestEntity> actionService);

    T read(String codeEntity, _RestService.ActionService<TestEntity> actionService);

    List<T> readAll(_RestService.ActionService<TestEntity> actionService);

    List<T> readByIds(List<Integer> ids, _RestService.ActionService<TestEntity> actionService);

    List<T> readAllActives(_RestService.ActionService<TestEntity> actionService);

    List<T> readAllActivesByIds(_RestService.ActionService<TestEntity> actionService);

    boolean create(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}
