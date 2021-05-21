package backend.gestion;

import backend.Gestion;
import database.MongoDb;

public class RechercheGestion extends Gestion{

	public RechercheGestion(MongoDb dataBaseConn) {
		super(dataBaseConn);
		// TODO Auto-generated constructor stub
	}
	
	//Objectif de cette classe : Faire une recherche qui va aller chercher tout les mots contenant la suite de lettres que l'utilisateur entre 
	// + faire une recherche par thématique
}
