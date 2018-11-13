/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author shan
 */
public class ConexaoMongoDB {

    private static ConexaoMongoDB uniqueInstance;

    public static ConexaoMongoDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConexaoMongoDB();
        }
        return uniqueInstance;
    }

    final private MongoDatabase database;

    private ConexaoMongoDB() {
        MongoClient client = new MongoClient("localhost", 27017);
        database = client.getDatabase("recimg");

    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
