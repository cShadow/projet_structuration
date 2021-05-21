package backend.gestion;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import backend.Gestion;
import database.MongoDb;


public class LoginGestion extends Gestion {



	public LoginGestion(MongoDb dataBaseConn) {
		super(dataBaseConn);
		// TODO Auto-generated constructor stub
	}

	public String login(String pseudo) {
		MongoDb mongo = this.getDataBaseConn();
		MongoDatabase data = mongo.getDatabase();
		System.out.println(data);
		System.out.println(data.listCollectionNames());

		MongoCollection<Document> collec = data.getCollection("utilisateur");
		Document doc = (Document) collec.find(Filters.eq("identifiant", pseudo)).first();
		return doc.toString();
	}
}
