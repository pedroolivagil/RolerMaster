package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToMany;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Unique;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;
import com.olivadevelop.rolermaster.tools.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
@Entity
@Persistence(collectionName = "gender")
public class Gender extends _BasicEntity {

    @Id
    private Integer idGender;

    @Unique
    private String code;

    @OneToMany(mappingClass = GenderTrans.class)
    @RelatedEntity(joinColumn = "idGender")
    private List<GenderTrans> translations;

    public Gender() {
        super();
        getTranslations();
    }

    public Gender(JSONObject json) throws JSONException {
        super(json);
        getTranslations();
    }

    public Integer getIdGender() {
        return idGender;
    }

    public void setIdGender(Integer idGender) {
        this.idGender = idGender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<GenderTrans> getTranslations() {
        if (Tools.isNull(translations)) {
            this.translations = new ArrayList<>();
        }
        return translations;
    }

    public void setTranslation(List<GenderTrans> translations) {
        this.translations = translations;
    }

    public void addTranslation(GenderTrans translation) {
        this.getTranslations().add(translation);
        /*translation.setGender(this);*/
        translation.setIdGender(getIdGender());
    }

    public void removeTranslation(GenderTrans translation) {
        this.getTranslations().remove(translation);
        /*translation.setGender(null);*/
        translation.setIdGender(null);
    }
}
