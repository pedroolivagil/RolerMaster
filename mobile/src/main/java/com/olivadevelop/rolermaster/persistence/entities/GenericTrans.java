package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Id;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

public class GenericTrans extends BasicEntity {

    @Id
    private Integer idTrans;
    private Integer idLocale;
    private String text;

    public GenericTrans() {
    }

    public GenericTrans(JSONObject json) throws JSONException {
        super(json);
    }

    public GenericTrans(Integer idLocale, Integer idTrans, String text) {
        this.idLocale = idLocale;
        this.idTrans = idTrans;
        this.text = text;
    }

    public Integer getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Integer idTrans) {
        this.idTrans = idTrans;
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
}
