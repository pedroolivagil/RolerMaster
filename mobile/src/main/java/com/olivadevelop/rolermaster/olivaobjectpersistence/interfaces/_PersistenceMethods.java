package com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces;

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

    T findOneByEntity(T entity) throws ExecutionException, InterruptedException;

    List<T> findByEntity(T entity) throws ExecutionException, InterruptedException;

    boolean persist(T entity) throws ExecutionException, InterruptedException, JSONException;

    boolean merge(T entity) throws ExecutionException, InterruptedException, JSONException;

    boolean remove(T entity) throws ExecutionException, InterruptedException, JSONException;
}
