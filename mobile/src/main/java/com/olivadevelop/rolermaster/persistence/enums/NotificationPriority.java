package com.olivadevelop.rolermaster.persistence.enums;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 26/01/2018.
 */

public enum NotificationPriority {
    HIGHT(1), MEDIUM(2), LOW(3), NONE(4);

    private Integer val;

    NotificationPriority(Integer val) {
        this.val = val;
    }

    public Integer val() {
        return val;
    }
}
