package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Id;
import com.olivadevelop.rolermaster.persistence.entities.annotations.OneToOne;
import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.persistence.entities.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 26/01/2018.
 */

@Persistence(collectionName = "PREFERENCE")
public class Preference extends BasicEntity {

    @Id
    private Integer idPreference;

    @OneToOne
    @RelatedEntity(joinColumn = "idUser")
    private User user;

    private String key;
    private String value;
    private Date date;

    public Preference() {
    }

    public Preference(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdPreference() {
        return idPreference;
    }

    public void setIdPreference(Integer idPreference) {
        this.idPreference = idPreference;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
