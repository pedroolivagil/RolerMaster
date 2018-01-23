package com.olivadevelop.rolermaster.persistence.entities.old;

import com.olivadevelop.rolermaster.tools.utils.intefraces.Entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class Country implements Entity {

    public Country() {
    }


    public Country(JSONObject json) throws JSONException {
        fillEntity(json);
    }

    @Override
    public void fillEntity(JSONObject json) {

    }
}
