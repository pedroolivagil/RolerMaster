package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.pojo.GenericTrans;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;
import com.olivadevelop.rolermaster.tools.utils.intefraces.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class Locale extends BasicEntity {

    private Integer idLocale;
    @Persistence(columnName = "codeISO")
    private String codeIso;
    private GenericTrans translation;

    public Locale() {
    }

    public Locale(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdLocale() {
        return idLocale;
    }

    public void setIdLocale(Integer idLocale) {
        this.idLocale = idLocale;
    }

    public String getCodeIso() {
        return codeIso;
    }

    public void setCodeIso(String codeIso) {
        this.codeIso = codeIso;
    }

    public GenericTrans getTranslation() {
        return translation;
    }

    public void setTranslation(GenericTrans translation) {
        this.translation = translation;
    }
}
