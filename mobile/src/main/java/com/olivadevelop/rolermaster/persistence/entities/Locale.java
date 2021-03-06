package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToMany;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
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
@Persistence(collectionName = "locale")
public class Locale extends _BasicEntity {

    @Id
    private Integer idLocale;

    @Persistence(columnName = "codeISO", unique = true)
    private String codeIso;

    @OneToMany(mappingClass = LocaleTrans.class)
    @RelatedEntity(joinColumn = "idLocale")
    private List<LocaleTrans> localeTrans;

    public Locale() {
        super();
        getLocaleTrans();
    }

    public Locale(JSONObject json) throws JSONException {
        super(json);
        getLocaleTrans();
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

    public List<LocaleTrans> getLocaleTrans() {
        if (Tools.isNull(localeTrans)) {
            this.localeTrans = new ArrayList<>();
        }
        return localeTrans;
    }

    public void setLocaleTrans(List<LocaleTrans> localeTrans) {
        this.localeTrans = localeTrans;
    }

    public void addLocaleTrans(LocaleTrans localeTrans) {
        this.getLocaleTrans().add(localeTrans);
        /*translation.setLocaleGroup(this);*/
        localeTrans.setIdLocaleGroup(getIdLocale());
    }

    public void removeLocaleTrans(LocaleTrans localeTrans) {
        this.getLocaleTrans().remove(localeTrans);
        /*translation.setLocaleGroup(null);*/
        localeTrans.setIdLocaleGroup(null);
    }
}
