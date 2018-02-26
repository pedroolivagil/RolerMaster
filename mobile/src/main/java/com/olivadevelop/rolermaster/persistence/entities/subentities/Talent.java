package com.olivadevelop.rolermaster.persistence.entities.subentities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Ability;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 28/01/2018.
 * RolerMaster
 */
@Entity
@Persistence(collectionName = "TALENT")
public class Talent extends _BasicEntity implements Ability {

    private Integer points;
    private String code;

    public Talent() {
        super();
    }

    public Talent(JSONObject json) throws JSONException {
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
