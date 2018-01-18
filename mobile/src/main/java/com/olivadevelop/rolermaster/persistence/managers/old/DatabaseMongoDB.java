package com.olivadevelop.rolermaster.persistence.managers.old;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.conversions.Bson;

/**
 * Crea y maneja las conexiones y consultas a la BBDD de MongoDB
 * <p>
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 17/01/2018.
 */

public final class DatabaseMongoDB {
    /*private static String DB_HOST = "192.168.1.34";*/
    /*private static String DB_PORT = "27018";*/
    /*private static String DB_USER = "admin";*/
    /*private static String DB_PASSWORD = "oreo_20081991_Aa";*/
    /*private static String DB_DB = "olivadeveloprolermaster";*/

    private static String DB_HOST = "ds261527.mlab.com";
    private static String DB_PORT = "61527";
    private static String DB_USER = "OlivaDevelop";
    private static String DB_PASSWORD = "20081991_Aa";
    private static String DB_DB = "rolermaster";

    private static final String URI_MONGO =
            "mongodb://" + DB_USER + ":" + DB_PASSWORD + "@" + DB_HOST + ":" + DB_PORT + "/" + DB_DB;
    private MongoDatabase db;
    private Class collection;

    private static DatabaseMongoDB instance = new DatabaseMongoDB();

    private DatabaseMongoDB() {
        MongoClientURI uri = new MongoClientURI(URI_MONGO);
        MongoClient mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase());
    }

    public static DatabaseMongoDB getInstance() {
        return instance;
    }

    /**
     * En Cada Controller se establecerá el nombre de la colección a la que irá asociada
     *
     * @param collection Nombre de la colección
     */
    public DatabaseMongoDB setCollectionName(Class collection) {
        this.collection = collection;
        return getInstance();
    }

    public void createCollection(String collectionName) {
        db.createCollection(collectionName);
    }

    public void removeCollection(String collectionName) {
        db.getCollection(collectionName).drop();
    }

    public void renameCollection(String oldName, String newName) {
        db.getCollection(oldName).renameCollection(new MongoNamespace(newName));
    }

    public MongoCollection getCurrentCollection() {
        return db.getCollection(collection.getSimpleName(), collection);
    }

    /* Methods for menage database */
    public long count() {
        MongoCollection collection = getCurrentCollection();
        return collection.count();
    }

    public long count(Bson query) {
        MongoCollection collection = getCurrentCollection();
        return collection.count(query);
    }

    public MongoCursor findAll() {
        MongoCollection collection = getCurrentCollection();
        return collection.find().iterator();
    }

    public MongoCursor findAllByKey(Bson query) {
        MongoCollection collection = getCurrentCollection();
        return collection.find(query).iterator();
    }

    public Object findOneByKey(Bson query) {
        MongoCollection collection = getCurrentCollection();
        return collection.find(query);
    }

    public boolean persist(Object entity) {
        boolean retorno = false;
        try {
            MongoCollection collection = getCurrentCollection();
            collection.insertOne(entity);
            retorno = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public boolean merge(Bson filter, Bson update) {
        boolean retorno = false;
        try {
            MongoCollection collection = getCurrentCollection();
            collection.updateOne(filter, update);
            retorno = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public boolean remove(Bson query) {
        boolean retorno = false;
        try {
            MongoCollection collection = getCurrentCollection();
            collection.deleteOne(query);
            retorno = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
}
