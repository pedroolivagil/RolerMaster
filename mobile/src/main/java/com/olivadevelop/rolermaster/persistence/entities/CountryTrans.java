package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */
@Entity
@Persistence(collectionName = "country_trans")
public class CountryTrans extends GenericTrans {

    /*@OneToOne(canPersist = false)
    @RelatedEntity(joinColumn = "idCountry", preference = true)
    private Country country;*/
    private Integer idCountry;

    public CountryTrans() {
        super();
    }

    public CountryTrans(JSONObject json) throws JSONException {
        super(json);
    }
/*
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }*/

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }
}
