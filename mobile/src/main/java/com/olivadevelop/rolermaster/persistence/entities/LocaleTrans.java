package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/02/2018.
 */

@Persistence(collectionName = "locale_trans")
public class LocaleTrans extends GenericTrans {

    @OneToOne
    @RelatedEntity(joinColumn = "idLocaleGroup", preference = true)
    private Locale localeGroup;

    public LocaleTrans(Locale localeGroup) {
        this.localeGroup = localeGroup;
    }

    public LocaleTrans(JSONObject json, Locale localeGroup) throws JSONException {
        super(json);
        this.localeGroup = localeGroup;
    }

    public Locale getLocaleGroup() {
        return localeGroup;
    }

    public void setLocaleGroup(Locale localeGroup) {
        this.localeGroup = localeGroup;
    }
}
