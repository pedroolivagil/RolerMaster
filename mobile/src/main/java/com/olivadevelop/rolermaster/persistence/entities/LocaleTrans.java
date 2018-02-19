package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/02/2018.
 */

@Persistence(collectionName = "locale_trans")
public class LocaleTrans extends GenericTrans {

    public LocaleTrans() {
    }

    public LocaleTrans(JSONObject json) throws JSONException {
        super(json);
    }

    public LocaleTrans(Integer idLocale, Integer idTrans, String text) {
        super(idLocale, idTrans, text);
    }

    @Override
    public Integer getIdTrans() {
        return super.getIdTrans();
    }

    @Override
    public void setIdTrans(Integer idTrans) {
        super.setIdTrans(idTrans);
    }

    @Override
    public Integer getIdLocale() {
        return super.getIdLocale();
    }

    @Override
    public void setIdLocale(Integer idLocale) {
        super.setIdLocale(idLocale);
    }

    @Override
    public String getText() {
        return super.getText();
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }
}
