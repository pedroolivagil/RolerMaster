package com.olivadevelop.rolermaster.tools.utils.intefraces;

import com.olivadevelop.rolermaster.persistence.entities.old.Entity;
import com.olivadevelop.rolermaster.persistence.managers._RestService;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public interface _PersistenceMethods<T> {

    T find(Integer idEntity, _RestService.ActionService<T> actionService) throws ExecutionException, InterruptedException, JSONException;

    List<T> findAll() throws ExecutionException, InterruptedException, JSONException;

    List<T> findAllByIds(List<Integer> idsEntity) throws ExecutionException, InterruptedException;

    T findOneByEntity(Entity entity) throws ExecutionException, InterruptedException;

    List<T> findByEntity(Entity entity) throws ExecutionException, InterruptedException;

    boolean persist(Entity entity) throws ExecutionException, InterruptedException;

    boolean merge(Entity entity) throws ExecutionException, InterruptedException;

    boolean remove(Entity entity) throws ExecutionException, InterruptedException;
}
