package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Unique;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
@Entity
@Persistence(collectionName = "game_category")
public class GameCategory extends _BasicEntity {

    @Id
    private Integer idGameCategory;

    @OneToOne(mappingClass = User.class)
    @RelatedEntity(joinColumn = "idUser")
    private User user;

    @Unique
    private String name;

    public GameCategory() {
        super();
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
