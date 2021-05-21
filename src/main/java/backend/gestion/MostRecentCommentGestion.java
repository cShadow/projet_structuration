package backend.gestion;

import backend.Gestion;
import database.MongoDb;

public class MostRecentCommentGestion extends Gestion{

	public MostRecentCommentGestion(MongoDb dataBaseConn) {
		super(dataBaseConn);
	}
}
