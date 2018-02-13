package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 28/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "PARAMETER")
public class Parameter extends BasicEntity {

    private Integer idParameter;
    private Integer idLocale;
    private String category;
    private GenericTrans translation;

    public Parameter() {
    }

    public Parameter(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(Integer idParameter) {
        this.idParameter = idParameter;
    }

    public Integer getIdLocale() {
        return idLocale;
    }

    public void setIdLocale(Integer idLocale) {
        this.idLocale = idLocale;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public GenericTrans getTranslation() {
        return translation;
    }

    public void setTranslation(GenericTrans translation) {
        this.translation = translation;
    }
}
