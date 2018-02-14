package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.annotations.Id;
import com.olivadevelop.rolermaster.persistence.entities.annotations.Persistence;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 14/02/2018.
 */
@Persistence(collectionName = "locale_trans")
public class LocaleTrans extends GenericTrans {
    @Id
    private Integer idLocale;

    @Override
    public Integer getIdLocale() {
        return idLocale;
    }

    @Override
    public void setIdLocale(Integer idLocale) {
        this.idLocale = idLocale;
    }
}
