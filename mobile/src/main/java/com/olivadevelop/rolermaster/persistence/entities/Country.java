package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.persistence.annotations.Id;
import com.olivadevelop.rolermaster.tools.persistence.annotations.OneToMany;
import com.olivadevelop.rolermaster.tools.persistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.tools.persistence.annotations.Persistence;
import com.olivadevelop.rolermaster.tools.persistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.tools.persistence.annotations.Unique;
import com.olivadevelop.rolermaster.tools.persistence.entities._BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "country")
public class Country extends _BasicEntity {

    @Id
    private Integer idCountry;
    @Unique
    private String code;

    @OneToMany
    @RelatedEntity(joinColumn = "idCountry")
    private List<CountryTrans> translation;

    @OneToOne
    @RelatedEntity(joinColumn = "idLocale", preference = true)
    private Locale locale;

    public Country() {
    }


    public Country(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CountryTrans> getTranslation() {
        return translation;
    }

    public void setTranslation(List<CountryTrans> translation) {
        this.translation = translation;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
