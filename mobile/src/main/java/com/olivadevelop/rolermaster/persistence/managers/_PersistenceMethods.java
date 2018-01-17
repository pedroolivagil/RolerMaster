package com.olivadevelop.rolermaster.persistence.managers;

import com.olivadevelop.rolermaster.persistence.entities.Entity;

import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 03/01/2018.
 */

public interface _PersistenceMethods<T> {

    T find(Integer idEntity);

    List<T> findAll();

    List<T> findAllByIds(List<Integer> idsEntity);

    T findOneByEntity(Entity entity);

    List<T> findByEntity(Entity entity);

    boolean persist(Entity entity);

    boolean merge(Entity entity);

    boolean remove(Entity entity);
}
