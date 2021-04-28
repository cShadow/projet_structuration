package main;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.FileReader;


public class Main {
	public static void main(String[] args) {
		// Creation de la connexion au serveur MongoDB
		MongoClient mongoClient = MongoClients.create();
		// Sélection de la base de données
		MongoDatabase database = mongoClient.getDatabase("data");
		// Selection de la collection
		MongoCollection<Document> collection = database.getCollection("data");

		char[] buffer = new char[100000];
		System.out.println("Hello");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		try {			
			FileReader file = new FileReader("import/Set_1/0.txt");
			file.read(buffer);
			System.out.println("Data in the file: ");
		    System.out.println(buffer);
			file.close();
		}
		catch (Exception e){
			System.out.println("Hi");
			e.printStackTrace();
		}

		// Insertion d'un document
		Document doc = new Document("name", "MongoDB")
				.append("type", "database")
				.append("count", 1)
				.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
				.append("info", new Document("x", 203).append("y", 102));
		collection.insertOne(doc);

	}
}
