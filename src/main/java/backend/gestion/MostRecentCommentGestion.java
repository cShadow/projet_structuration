package backend.gestion;

import backend.Gestion;
import database.MongoDb;

public class MostRecentCommentGestion extends Gestion{

	public MostRecentCommentGestion(MongoDb dataBaseConn) {
		super(dataBaseConn);
		// TODO Auto-generated constructor stub
	}
	
	//Objectif de cette classe : Retourner les 10 oeuvres les plus récemment commenté (en fonction du role, de l'université et de la licence)
	
}
