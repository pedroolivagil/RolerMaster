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

    public Country() {
    }


    public Country(JSONObject json) throws JSONException {
        super(json);
    }

    @Override
    public void fillEntity(JSONObject json) {
        if (Tools.isNotNull(json)) {
        }
    }
}
