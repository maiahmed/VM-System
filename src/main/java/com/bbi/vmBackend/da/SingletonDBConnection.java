package com.bbi.vmBackend.da;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class SingletonDBConnection {
	private final static String DBURL = "jdbc:mysql://localhost:3306/vm";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "20130334";
	private Connection jdbcConnection;
	private SingletonDBConnection instance;

	private void DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.jdbcConnection = DriverManager.getConnection(DBURL, USERNAME,
					PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("Database Connection Creation Failed : "
					+ e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database Connection Creation Failed : "
					+ e.getMessage());
			e.printStackTrace();
		}

	}

	public SingletonDBConnection getInstance() {
		if (instance == null)
			instance = new SingletonDBConnection();
		else
			try {
				if (instance.getConnection().isClosed()) {
					instance = new SingletonDBConnection();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return instance;
	}

	public Connection getConnection() {
		return jdbcConnection;
	}
}
