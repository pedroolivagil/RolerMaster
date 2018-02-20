package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.persistence.annotations.Id;
import com.olivadevelop.rolermaster.tools.persistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.tools.persistence.annotations.Persistence;
import com.olivadevelop.rolermaster.tools.persistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.tools.persistence.entities._BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 28/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "parameter")
public class Parameter extends _BasicEntity {

    @Id
    private Integer idParameter;

    @OneToOne
    @RelatedEntity(joinColumn = "idLocale")
    private Locale locale;

    private String category;

    private String text;

    public Parameter() {
    }

    public Parameter(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(Integer idParameter) {
        this.idParameter = idParameter;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
