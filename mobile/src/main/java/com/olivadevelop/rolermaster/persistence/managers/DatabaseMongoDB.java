package com.olivadevelop.rolermaster.persistence.managers;

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
    private static String DB_HOST = "192.168.1.34";
    private static String DB_USER = "admin";
    private static String DB_PASSWORD = "oreo_20081991_Aa";
    private static String DB_DB = "olivadeveloprolermaster";
    private static String DB_PORT = "27018";

    public static final String URI_MONGO =
            "mongodb://" + DB_USER + ":" + DB_PASSWORD + "@" + DB_HOST + ":" + DB_PORT + "/" + DB_DB;
    private MongoClient mongoClient;
    private MongoDatabase db;
    private String collectionName;

    private static DatabaseMongoDB instance = new DatabaseMongoDB();

    private DatabaseMongoDB() {
        MongoClientURI uri = new MongoClientURI(URI_MONGO);
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase());
    }

    public static DatabaseMongoDB getInstance() {
        return instance;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
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

    /* Methods for menage database */
    public long count() {
        MongoCollection<org.bson.Document> collection = db.getCollection(collectionName);
        return collection.count();
    }

    public long count(Bson query) {
        MongoCollection<org.bson.Document> collection = db.getCollection(collectionName);
        return collection.count(query);
    }

    public MongoCursor<org.bson.Document> findAll() {
        MongoCollection<org.bson.Document> collection = db.getCollection(collectionName);
        return collection.find().iterator();
    }

    public MongoCursor<org.bson.Document> findAllByKey(Bson query) {
        MongoCollection<org.bson.Document> collection = db.getCollection(collectionName);
        return collection.find(query).iterator();
    }

    public Object findOneByKey(Bson query) {
        MongoCollection<org.bson.Document> collection = db.getCollection(collectionName);
        return collection.find(query);
    }

    public boolean persist(Object entity) {
        boolean retorno = false;
        try {
            MongoCollection collection = db.getCollection(collectionName);
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
            MongoCollection<org.bson.Document> collection = db.getCollection(collectionName);
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
            MongoCollection<org.bson.Document> collection = db.getCollection(collectionName);
            collection.deleteOne(query);
            retorno = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
}
