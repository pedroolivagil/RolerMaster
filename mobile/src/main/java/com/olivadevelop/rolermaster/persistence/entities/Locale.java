package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Id;
import com.olivadevelop.rolermaster.persistence.entities.annotations.ManyToOne;
import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.persistence.entities.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "locale")
public class Locale extends BasicEntity {

    @Id
    private Integer idLocale;

    @Persistence(columnName = "codeISO", unique = true)
    private String codeIso;

    @ManyToOne
    @RelatedEntity(joinColumn = "idLocale")
    private List<LocaleTrans> translations;

    public Locale() {
    }

    public Locale(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdLocale() {
        return idLocale;
    }

    public void setIdLocale(Integer idLocale) {
        this.idLocale = idLocale;
    }

    public String getCodeIso() {
        return codeIso;
    }

    public void setCodeIso(String codeIso) {
        this.codeIso = codeIso;
    }

    public List<LocaleTrans> getTranslations() {
        return translations;
    }

    public void setTranslations(List<LocaleTrans> translations) {
        this.translations = translations;
    }
}
