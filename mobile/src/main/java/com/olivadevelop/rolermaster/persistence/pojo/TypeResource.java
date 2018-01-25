package com.olivadevelop.rolermaster.persistence.pojo;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 26/01/2018.
 */

public enum TypeResource {
    GIF(1), JPG(2), JPEG(3), PNG(4), PDF(5), TXT(6);

    private Integer val;

    TypeResource(Integer val) {
        this.val = val;
    }

    public Integer val() {
        return val;
    }
}
