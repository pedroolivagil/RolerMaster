package com.olivadevelop.rolermaster.olivaobjectpersistence.annotations;

import java.lang.annotation.*;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 19/02/2018.
 */

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToOne {

    boolean canPersist() default true;
}
