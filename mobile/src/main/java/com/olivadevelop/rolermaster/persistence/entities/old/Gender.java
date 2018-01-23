package com.olivadevelop.rolermaster.persistence.entities.old;

import com.olivadevelop.rolermaster.tools.utils.intefraces.Entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class Gender implements Entity {


    public Gender() {
    }

    public Gender(JSONObject json) throws JSONException {
        fillEntity(json);
    }

    @Override
    public void fillEntity(JSONObject json) {

    }
}
