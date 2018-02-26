package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.*;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.*;

import java.util.*;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
@Entity
@Persistence(collectionName = "country")
public class Country extends _BasicEntity {

    @Id
    private Integer idCountry;
    @Unique
    private String code;

    @OneToMany(mappingClass = CountryTrans.class)
    @RelatedEntity(joinColumn = "idCountry")
    private List<CountryTrans> translations;

    @OneToOne
    @RelatedEntity(joinColumn = "idLocale", preference = true)
    private Locale locale;

    public Country() {
        super();
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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<CountryTrans> getTranslations() {
        if (Tools.isNull(translations)) {
            this.translations = new ArrayList<>();
        }
        return translations;
    }

    public void setTranslations(List<CountryTrans> translations) {
        this.translations = translations;
    }

    public void addTranslation(CountryTrans translation) {
        this.getTranslations().add(translation);
        translation.setCountry(this);
    }

    public void removeTranslation(CountryTrans translation) {
        this.getTranslations().remove(translation);
        translation.setCountry(null);
    }
}
