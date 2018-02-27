package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */
@Entity
@Persistence(collectionName = "generic_trans")
public class GenericTrans extends _BasicEntity {

    @Id
    private Integer idTrans;

    /*@OneToOne(canPersist = false)
    @RelatedEntity(joinColumn = "idLocale", preference = true)
    private Locale locale;*/
    private Integer idLocale;

    private String text;

    GenericTrans() {
        super();
    }

    GenericTrans(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Integer idTrans) {
        this.idTrans = idTrans;
    }
/*
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }*/

    public Integer getIdLocale() {
        return idLocale;
    }

    public void setIdLocale(Integer idLocale) {
        this.idLocale = idLocale;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
