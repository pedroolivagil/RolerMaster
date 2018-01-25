package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
public class Game extends BasicEntity {

    public Game() {
    }

    public Game(JSONObject json) throws JSONException {
        super(json);
    }

    @Override
    public void onConstruct(JSONObject json) throws JSONException {

    }
}
