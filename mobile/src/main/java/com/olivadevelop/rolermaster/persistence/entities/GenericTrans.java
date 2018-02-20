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
 * Created by Oliva on 02/01/2018.
 */
@Persistence(collectionName = "generic_trans")
public class GenericTrans extends _BasicEntity {

    @Id
    private Integer idTrans;

    @OneToOne
    @RelatedEntity(joinColumn = "idLocale", preference = true)
    private Locale locale;

    private String text;

    GenericTrans() {
    }

    GenericTrans(JSONObject json) throws JSONException {
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
