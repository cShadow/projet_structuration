package backend.gestion;

import backend.Gestion;
import database.MongoDb;

public class MostRatedGestion extends Gestion {

	public MostRatedGestion(MongoDb dataBaseConn) {
		super(dataBaseConn);
		// TODO Auto-generated constructor stub
	}

	
	/* Demande : 
	 * 1) Liste des 10 oeuvres les mieux notees
	 * 2) Liste des 10 oeuvres les plus recemment commentes
	 * 3) Pouvoir rechercher une oeuvre en fonction de : sa thematique, son titre, mot dans le contenu
	 * 4) Pouvoir commenter une oeuvre et noter une oeuvre
	 * 5) Pouvoir se connecter avec son login (peut etre rajouter un mdp?) 
	 * 
	 * Solutions techniques :
	 * 1) et 2) Fonction qui va faire une requete dans la base pour retourner une liste des oeuvres 
	 * 3) Fonction recherche de mot qui va aller lire tout les fichiers de la base pour retourner ce qui concordent
	 * 4) Attributs a rajouter dans l'oeuvre correspondant a une liste de combinaison de string et d'id de l'utilisateur
	 * 5) Fonction pour se connecter (internet)
	 * */
}
