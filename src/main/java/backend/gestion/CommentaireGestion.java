package backend.gestion;

import backend.Gestion;
import database.MongoDb;

public class CommentaireGestion extends Gestion {

	public CommentaireGestion(MongoDb dataBaseConn) {
		super(dataBaseConn);
		// TODO Auto-generated constructor stub	
	}
	
	public void test () {
		System.out.println("bite");
	}
	//Objectif de cette classe : ajout d'un commentaire pour une oeuvre et d'une note sur 5.

}
