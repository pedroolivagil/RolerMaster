package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;
import com.olivadevelop.rolermaster.persistence.enums.TypeResource;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
@Entity
@Persistence(collectionName = "resource")
public class Resource extends _BasicEntity {

    private byte[] file;

    @Id
    private Integer idResource;

    @OneToOne(mappingClass = User.class)
    @RelatedEntity(joinColumn = "idUserUpload")
    private User userUpload;

    private String code;
    private Date date;
    private TypeResource typeResource;

    public Resource() {
        super();
    }

    public Resource(JSONObject json) throws JSONException {
        super(json);
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Integer getIdResource() {
        return idResource;
    }

    public void setIdResource(Integer idResource) {
        this.idResource = idResource;
    }

    public User getUserUpload() {
        return userUpload;
    }

    public void setUserUpload(User userUpload) {
        this.userUpload = userUpload;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeResource getTypeResource() {
        return typeResource;
    }

    public void setTypeResource(TypeResource typeResource) {
        this.typeResource = typeResource;
    }
}
