package backend.gestion;

import backend.Gestion;
import database.MongoDb;

public class MostRatedGestion extends Gestion {

	public MostRatedGestion(MongoDb dataBaseConn) {
		super(dataBaseConn);
		// TODO Auto-generated constructor stub
	}

	
	//Objectif de cette classe : Retourner les 10 oeuvres les mieux notées (en fonction du role, de l'université et de la licence)
}
