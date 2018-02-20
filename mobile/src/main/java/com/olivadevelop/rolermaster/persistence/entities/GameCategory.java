package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Id;
import com.olivadevelop.rolermaster.persistence.entities.annotations.OneToOne;
import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.persistence.entities.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.persistence.entities.annotations.Unique;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "game_category")
public class GameCategory extends BasicEntity {

    @Id
    private Integer idGameCategory;

    @OneToOne
    @RelatedEntity(joinColumn = "idUser")
    private User user;

    @Unique
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
