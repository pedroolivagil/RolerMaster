package com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces;

import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceDAO;

import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 22/01/2018.
 */

public interface ControllerMethods<T> {

    T read(Integer idEntity, ServiceDAO.ActionService<T> actionService);

    T read(String codeEntity, ServiceDAO.ActionService<T> actionService);

    List<T> readAll(ServiceDAO.ActionService<List<T>> actionService);

    List<T> readByIds(List<Integer> ids, ServiceDAO.ActionService<List<T>> actionService);

    List<T> readAllActives(ServiceDAO.ActionService<List<T>> actionService);

    List<T> readAllActivesByIds(List<Integer> ids, ServiceDAO.ActionService<List<T>> actionService);

    boolean create(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}
