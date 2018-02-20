package com.olivadevelop.rolermaster.tools.persistence.managers;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 20/02/2018.
 */

public enum ServiceURL {
    READ("read.php"),
    CREATE("create.php"),
    DELETE("delete.php"),
    UPDATE("update.php");

    private String url;

    ServiceURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
