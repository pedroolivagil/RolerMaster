package com.olivadevelop.rolermaster.olivaobjectpersistence.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

public interface Entity extends Serializable {

    void toEntity(JSONObject json) throws JSONException;

    JSONObject toJSONPersistence() throws JSONException;

    JSONObject toJSON(boolean fullObject) throws JSONException;

    String generateCode();

}
