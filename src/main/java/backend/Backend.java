package backend;

import backend.gestion.CommentaireGestion;
import backend.gestion.LoginGestion;
import backend.gestion.MostRatedGestion;
import backend.gestion.MostRecentCommentGestion;
import backend.gestion.RechercheGestion;
import database.MongoDb;

public class Backend {
	
	private static Backend myBackend;
	
	private static MongoDb mongoDbConn;
	
	public static CommentaireGestion getCommentaireGestion() {
		return new CommentaireGestion(mongoDbConn);
	}
	
	public static LoginGestion getLoginGestion() {
		return new LoginGestion(mongoDbConn);
	}
	
	public static MostRatedGestion getMostRatedGestion() {
		return new MostRatedGestion(mongoDbConn);
	}
	
	public static MostRecentCommentGestion getMostRecentCommentGestion() {
		return new MostRecentCommentGestion(mongoDbConn);
	}
	
	public static RechercheGestion getRechercheGestion() {
		return new RechercheGestion(mongoDbConn);
	}
	

	public static void createBackend() throws Exception {
		if (myBackend == null)
			myBackend = new Backend();
	}
	
	public Backend() throws Exception {
		mongoDbConn = new MongoDb();
	}
}
