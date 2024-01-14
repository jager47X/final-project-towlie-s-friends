package dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoConnection {

    private static MongoClient mongoClient = new MongoClient("localhost", 27017);
    public static MongoClient GetClient(){return mongoClient;};
    public static MongoDatabase getDb(){
        return mongoClient.getDatabase("demoDB");
    }

    public static MongoCollection<Document> getCollection(String collectionName){
        return getDb().getCollection(collectionName);
    }

}
