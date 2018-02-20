package com.olivadevelop.rolermaster.persistence.entities.subentities;

import com.olivadevelop.rolermaster.persistence.entities.interfaces.Attribute;
import com.olivadevelop.rolermaster.tools.persistence.annotations.Persistence;
import com.olivadevelop.rolermaster.tools.persistence.entities._BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 28/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "PHYSICAL")
public class Physical extends _BasicEntity implements Attribute {

    private Integer points;
    private String code;

    public Physical() {
    }

    public Physical(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
