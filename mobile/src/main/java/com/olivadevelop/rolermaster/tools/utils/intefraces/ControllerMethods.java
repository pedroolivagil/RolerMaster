package com.olivadevelop.rolermaster.tools.utils.intefraces;

import com.olivadevelop.rolermaster.persistence.managers._RestService;

import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 22/01/2018.
 */

public interface ControllerMethods<T> {

    T read(Integer idEntity, _RestService.ActionService<T> actionService);

    T read(String codeEntity, _RestService.ActionService<T> actionService);

    List<T> readAll(_RestService.ActionService<T> actionService);

    List<T> readByIds(List<Integer> ids, _RestService.ActionService<T> actionService);

    List<T> readAllActives(_RestService.ActionService<T> actionService);

    List<T> readAllActivesByIds(_RestService.ActionService<T> actionService);

    boolean create(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}
