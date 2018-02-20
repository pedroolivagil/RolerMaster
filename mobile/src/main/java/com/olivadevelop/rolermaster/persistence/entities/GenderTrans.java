package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.OneToOne;
import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.persistence.entities.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

@Persistence(collectionName = "gender_trans")
public class GenderTrans extends GenericTrans {

    @OneToOne
    @RelatedEntity(joinColumn = "idGender", preference = true)
    private Gender gender;

    public GenderTrans() {
    }

    public GenderTrans(JSONObject json) throws JSONException {
        super(json);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}