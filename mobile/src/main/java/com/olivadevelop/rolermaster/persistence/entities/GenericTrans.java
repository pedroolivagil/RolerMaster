package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Id;
import com.olivadevelop.rolermaster.persistence.entities.annotations.OneToOne;
import com.olivadevelop.rolermaster.persistence.entities.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 02/01/2018.
 */

public class GenericTrans extends BasicEntity {

    @Id
    private Integer idTrans;
    @OneToOne
    @RelatedEntity(joinColumn = "idLocale", preference = true)
    private Locale locale;
    private String text;

    public GenericTrans() {
    }

    public GenericTrans(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Integer idTrans) {
        this.idTrans = idTrans;
    }

    public Locale getLocaleGroup() {
        return locale;
    }

    public void setLocaleGroup(Locale localeGroup) {
        this.locale = localeGroup;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
