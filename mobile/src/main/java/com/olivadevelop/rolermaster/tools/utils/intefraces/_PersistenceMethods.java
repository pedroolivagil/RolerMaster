package com.olivadevelop.rolermaster.tools.utils.intefraces;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public interface _PersistenceMethods<T> {

    T find(Integer idEntity) throws ExecutionException, InterruptedException, JSONException;

    T find(FormBody query) throws ExecutionException, InterruptedException, JSONException;

    List<T> findAll() throws ExecutionException, InterruptedException, JSONException;

    List<T> findAll(FormBody query) throws ExecutionException, InterruptedException, JSONException;

    List<T> findAllByIds(List<Integer> idsEntity) throws ExecutionException, InterruptedException;

    T findOneByEntity(Entity entity) throws ExecutionException, InterruptedException;

    List<T> findByEntity(Entity entity) throws ExecutionException, InterruptedException;

    boolean persist(Entity entity) throws ExecutionException, InterruptedException;

    boolean merge(Entity entity) throws ExecutionException, InterruptedException;

    boolean remove(Entity entity) throws ExecutionException, InterruptedException;
}
