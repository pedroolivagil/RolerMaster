package com.olivadevelop.rolermaster.persistence.entities.old;

import org.bson.conversions.Bson;

/**
 * Created by Oliva on 02/01/2018.
 */

public interface Entity {

    Bson toBson();
}
