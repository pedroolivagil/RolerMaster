package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.persistence.annotations.Id;
import com.olivadevelop.rolermaster.tools.persistence.annotations.OneToMany;
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
@Persistence(collectionName = "gender")
public class Gender extends _BasicEntity {

    @Id
    private Integer idGender;

    @Unique
    private String code;

    @OneToMany
    @RelatedEntity(joinColumn = "idGender")
    private List<GenderTrans> translation;

    public Gender() {
    }

    public Gender(JSONObject json) throws JSONException {
        super(json);
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

    public List<GenderTrans> getTranslation() {
        return translation;
    }

    public void setTranslation(List<GenderTrans> translation) {
        this.translation = translation;
    }
}
