package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/02/2018.
 */

@Entity
@Persistence(collectionName = "locale_trans")
public class LocaleTrans extends GenericTrans {
/*
    @OneToOne(canPersist = false)
    @RelatedEntity(joinColumn = "idLocaleGroup", preference = true)
    private Locale localeGroup;*/

    private Integer idLocaleGroup;

    public LocaleTrans() {
        super();
    }

    public LocaleTrans(JSONObject json) throws JSONException {
        super(json);
    }
/*
    public Locale getLocaleGroup() {
        return localeGroup;
    }

    public void setLocaleGroup(Locale localeGroup) {
        this.localeGroup = localeGroup;
    }*/

    public Integer getIdLocaleGroup() {
        return idLocaleGroup;
    }

    public void setIdLocaleGroup(Integer idLocaleGroup) {
        this.idLocaleGroup = idLocaleGroup;
    }
}
