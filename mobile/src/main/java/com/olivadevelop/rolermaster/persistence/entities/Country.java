package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.interfaces.Persistence;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.RelatedEntity;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "COUNTRY")
public class Country extends BasicEntity {

    private Integer idCountry;
    private String code;
    private GenericTrans translation;
    @RelatedEntity
    private Locale locale;

    public Country() {
    }


    public Country(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GenericTrans getTranslation() {
        return translation;
    }

    public void setTranslation(GenericTrans translation) {
        this.translation = translation;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
