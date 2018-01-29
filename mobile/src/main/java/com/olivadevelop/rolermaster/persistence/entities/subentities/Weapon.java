package com.olivadevelop.rolermaster.persistence.entities.subentities;

import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 28/01/2018.
 * RolerMaster
 */
public class Weapon extends BasicEntity {
    public Weapon() {
    }

    public Weapon(JSONObject json) throws JSONException {
        super(json);
    }
}
