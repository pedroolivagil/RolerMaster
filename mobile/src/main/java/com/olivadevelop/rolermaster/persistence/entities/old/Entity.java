package com.olivadevelop.rolermaster.persistence.entities.old;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

public interface Entity {

    void fillEntity (JSONObject json) throws JSONException;

}
