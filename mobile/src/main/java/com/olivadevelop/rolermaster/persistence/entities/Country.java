package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class Country extends BasicEntity {

    private Integer idCountry;
    private String code;
    private GenericTrans tranlation;
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

    public GenericTrans getTranlation() {
        return tranlation;
    }

    public void setTranlation(GenericTrans tranlation) {
        this.tranlation = tranlation;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void construct(JSONObject json) throws JSONException {
        if (Tools.isNotNull(json)) {
            this.idCountry = json.getInt("idCountry");
            this.code = json.getString("code");
            this.tranlation = new GenericTrans(json.getJSONObject(COMMON_FIELD_TRANS));
            this.locale = new Locale(json.getJSONObject("locale"));
        }
    }
}
