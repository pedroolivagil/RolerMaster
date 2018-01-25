package com.olivadevelop.rolermaster.tools.utils.intefraces;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

public interface Entity extends Serializable {

    void toEntity(JSONObject json) throws JSONException;

    String generateCode();

}
