package com.olivadevelop.rolermaster.olivaobjectpersistence.utils;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces.Entity;

import okhttp3.FormBody;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 23/03/2018.
 * RolerMaster
 */
class JoinQuery<T extends _BasicEntity> {

    private Entity entity;
    private Entity entityJoin;

    public JoinQuery(Entity entity, Entity entityJoin) {
        this.entity = entity;
        this.entityJoin = entityJoin;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntityJoin() {
        return entityJoin;
    }

    public void setEntityJoin(Entity entityJoin) {
        this.entityJoin = entityJoin;
    }

    public FormBody.Builder toForm(FormBody.Builder query, boolean restrict) {
        try {
            if (Utils.isNull(query)) {
                query = new FormBody.Builder();
            }
            String tableName = entity.getClass().getAnnotation(Persistence.class).collectionName();
            String tablePk = Utils.getPkFromEntity(entity).getKey();
            String joinTableName = entityJoin.getClass().getAnnotation(Persistence.class).collectionName();
            String joinTablePk = Utils.getPkFromEntity(entityJoin).getKey();
            query.add("join[][entityParent]", tableName);
            query.add("join[][entityJoin]", joinTableName);
            query.add("join[][pkParent]", tablePk);
            query.add("join[][pkJoin]", joinTablePk);
            query.add("join[][restrict]", String.valueOf(restrict));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return query;
    }
}
