package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class Gender extends BasicEntity {


    private Integer idGender;
    private String code;
    private GenericTrans translation;

    public Gender() {
    }

    public Gender(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdGender() {
        return idGender;
    }

    public void setIdGender(Integer idGender) {
        this.idGender = idGender;
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

    @Override
    public void onConstruct(JSONObject json) throws JSONException {
        this.idGender = json.getInt("idGender");
        this.code = json.getString("code");
        this.translation = new GenericTrans(json.getJSONObject(COMMON_FIELD_TRANS));
    }
}
