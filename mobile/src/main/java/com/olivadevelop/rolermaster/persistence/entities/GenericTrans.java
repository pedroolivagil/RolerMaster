package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

public class GenericTrans extends BasicEntity {

    private Integer idLocale;
    private String text;

    public GenericTrans() {
    }

    public GenericTrans(JSONObject json) throws JSONException {
        super(json);
    }

    public GenericTrans(Integer idLocale, String text) {
        this.idLocale = idLocale;
        this.text = text;
    }

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

    @Override
    public void onConstruct(JSONObject json) throws JSONException {
        this.idLocale = json.getInt("idLocale");
        this.text = json.getString("text");
    }
}
