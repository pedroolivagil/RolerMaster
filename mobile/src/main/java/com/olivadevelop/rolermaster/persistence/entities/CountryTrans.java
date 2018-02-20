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
@Persistence(collectionName = "country_trans")
public class CountryTrans extends BasicEntity {

    @OneToOne
    @RelatedEntity(joinColumn = "idCountry", preference = true)
    private Country country;

    public CountryTrans() {
    }

    public CountryTrans(JSONObject json) throws JSONException {
        super(json);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
