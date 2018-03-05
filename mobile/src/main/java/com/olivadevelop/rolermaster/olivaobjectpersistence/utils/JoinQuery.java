package com.olivadevelop.rolermaster.olivaobjectpersistence.utils;

import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 23/03/2018.
 * RolerMaster
 */
class JoinQuery<T extends _BasicEntity> {

    private Class<T> entity;
    private Class<T> entityJoin;

    public JoinQuery(Class<T> entity, Class<T> entityJoin) {
        this.entity = entity;
        this.entityJoin = entityJoin;
    }

    public Class<T> getEntity() {
        return entity;
    }

    public void setEntity(Class<T> entity) {
        this.entity = entity;
    }

    public Class<T> getEntityJoin() {
        return entityJoin;
    }

    public void setEntityJoin(Class<T> entityJoin) {
        this.entityJoin = entityJoin;
    }

    public FormBody.Builder toForm(FormBody.Builder query) {
        if (Utils.isNull(query)) {
            query = new FormBody.Builder();
        }
        query.add("", "");
        query.add("", "");
        query.add("", "");
        query.add("", "");
        return query;
    }
}
