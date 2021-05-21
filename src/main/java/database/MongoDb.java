package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDb {

	//attributs
	private MongoDatabase database;
	
	public MongoDb() {
		super();
		MongoClient client = MongoClients.create("mongodb://localhost:27017");
		this.database = client.getDatabase("data");
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}
	
	
}
