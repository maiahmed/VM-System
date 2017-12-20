package com.bbi.vmBackend.da;

import java.sql.Connection;

public abstract class DBConnection extends SingletonDBConnection {
	
	private SingletonDBConnection dbConnection;  //composition has-a relationship
	
	public Connection getConnection() {
		return dbConnection.getConnection();
	}
	
}
