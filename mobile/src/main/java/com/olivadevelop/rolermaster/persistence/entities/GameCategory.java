package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.interfaces.Persistence;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "GAME_CATEGORY")
public class GameCategory extends BasicEntity {

    private Integer idGameCategory;
    private Integer idUser;
    private String name;

    public GameCategory() {
    }

    public GameCategory(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdGameCategory() {
        return idGameCategory;
    }

    public void setIdGameCategory(Integer idGameCategory) {
        this.idGameCategory = idGameCategory;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
