package backend;

import database.MongoDb;

public class Gestion {
	
	private MongoDb dataBaseConn;
	
	public Gestion(MongoDb dataBaseConn) {
		this.dataBaseConn = dataBaseConn;
	}

	public MongoDb getDataBaseConn() {
		return dataBaseConn;
	}
	
}
