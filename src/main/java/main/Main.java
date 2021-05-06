package main;

import java.util.Arrays;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import business.Oeuvre;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.CharBuffer;


public class Main {
	public static void main(String[] args) throws IOException {
		// Creation de la connexion au serveur MongoDB
		MongoClient mongoClient = MongoClients.create();
		// Sélection de la base de données
		MongoDatabase database = mongoClient.getDatabase("data");
		// Selection de la collection
		MongoCollection<Document> collection = database.getCollection("data");
		
		s
		
/*
		// Insertion d'un document
		Document doc = new Document("name", "MongoDB")
				.append("type", "database")
				.append("count", 1)
				.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
				.append("info", new Document("x", 203).append("y", 102));
		collection.insertOne(doc);
*/
	}
}
