package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.Tools;
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

    @Override
    public void fillEntity(JSONObject json) throws JSONException {
        if (Tools.isNotNull(json)) {
            this.idLocale = json.getInt("idLocale");
            this.text = json.getString("text");
        }
    }
}
