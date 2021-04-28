package main;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public class Main {
	public static void main(String[] args) {
		// Creation de la connexion au serveur MongoDB
		MongoClient mongoClient = MongoClients.create();
		// Sélection de la base de données
		MongoDatabase database = mongoClient.getDatabase("data");
		// Selection de la collection
		MongoCollection<Document> collection = database.getCollection("data");
		
		// Insertion d'un document
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);
		
	}
}
