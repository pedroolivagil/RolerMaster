package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;
import com.olivadevelop.rolermaster.persistence.pojo.TypeResource;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 25/01/2018.
 * RolerMaster
 */
@Persistence(collectionName = "RESOURCE")
public class Resource extends BasicEntity {

    private byte[] file;
    private Integer idResource;
    private Integer idUserUpload;
    private String code;
    private Date date;
    private TypeResource typeResource;

    public Resource() {
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

    public Integer getIdUserUpload() {
        return idUserUpload;
    }

    public void setIdUserUpload(Integer idUserUpload) {
        this.idUserUpload = idUserUpload;
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
