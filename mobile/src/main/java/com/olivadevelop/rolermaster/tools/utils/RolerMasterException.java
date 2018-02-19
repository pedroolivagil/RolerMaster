package com.olivadevelop.rolermaster.tools.utils;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/02/2018.
 */

public class RolerMasterException extends Exception {
    public enum TypeException {
        PERSISTENCE, CODE
    }

    private TypeException typeException;

    public RolerMasterException(TypeException typeException) {
        this.typeException = typeException;
    }

    public RolerMasterException(TypeException typeException, String message) {
        super(message);
        this.typeException = typeException;
    }

    public RolerMasterException(String message, TypeException typeException) {
        super(message);
        this.typeException = typeException;
    }

    public RolerMasterException(String message, Throwable cause, TypeException typeException) {
        super(message, cause);
        this.typeException = typeException;
    }

    public RolerMasterException(Throwable cause, TypeException typeException) {
        super(cause);
        this.typeException = typeException;
    }

    public RolerMasterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, TypeException typeException) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.typeException = typeException;
    }
}
