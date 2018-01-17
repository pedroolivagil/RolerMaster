package com.olivadevelop.rolermaster.persistence.managers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Crea y maneja las conexiones y consultas a la BBDD de MongoDB
 * <p>
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 17/01/2018.
 */

public final class DatabaseMongoDB {
    MongoClientURI uri = new MongoClientURI("mongodb://username:password@www.example.com:12345/db-name");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());
}
