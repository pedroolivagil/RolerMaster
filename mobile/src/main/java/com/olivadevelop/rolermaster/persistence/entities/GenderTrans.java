package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

@Entity
@Persistence(collectionName = "gender_trans")
public class GenderTrans extends GenericTrans {

    @OneToOne
    @RelatedEntity(joinColumn = "idGender", preference = true)
    private Gender gender;

    public GenderTrans() {
        super();
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
