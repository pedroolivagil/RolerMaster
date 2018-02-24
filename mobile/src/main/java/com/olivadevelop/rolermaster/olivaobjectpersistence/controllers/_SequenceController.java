package com.olivadevelop.rolermaster.olivaobjectpersistence.controllers;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceDAO;
import com.olivadevelop.rolermaster.olivaobjectpersistence.managers.ServiceURL;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.KeyValuePair;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.QueryBuilder;
import com.olivadevelop.rolermaster.olivaobjectpersistence.utils.ToolsOlivaDevelop;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 24/02/2018.
 * RolerMaster
 */
final class _SequenceController<T extends _BasicEntity> {
    private QueryBuilder<T> queryBuilder;

    _SequenceController(Class entity) {
        this.queryBuilder = new QueryBuilder<>(entity);
    }

    /**
     * Consulta en bbdd el último valor de la secuencia
     *
     * @param entity
     * @return
     */
    Integer getNextval(T entity) throws ExecutionException, InterruptedException, JSONException {
        KeyValuePair<String, Object> query = new KeyValuePair<>();
        query.setKey("nameSequence");
        Persistence persistence = entity.getClass().getAnnotation(Persistence.class);
        String nameSequence;
        if (ToolsOlivaDevelop.isNotNull(persistence) && ToolsOlivaDevelop.isNotNull(persistence.secuenceName())) {
            nameSequence = persistence.secuenceName();
        } else {
            nameSequence = entity.getClass().getSimpleName().toLowerCase().trim();
        }
        query.setValue(nameSequence);
        JSONObject result = ServiceDAO.getInstance().newCall(ServiceURL.SEQUENCE, this.queryBuilder.createSimpleQuery(Collections.singletonList(query)));
        return result.getInt("sequence");
    }
}
