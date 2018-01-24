package com.olivadevelop.rolermaster.tools.utils;

import com.olivadevelop.rolermaster.tools.utils.intefraces.Entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/01/2018.
 */

public abstract class BasicEntity implements Entity {

    public BasicEntity() {
    }

    public BasicEntity(JSONObject json) throws JSONException {
        fillEntity(json);
    }
}
