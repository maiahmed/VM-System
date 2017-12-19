package com.bbi.vmBackend.da;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBConnection {
	private static String dbURL = "jdbc:mysql://localhost:3306/vm";
	private static String username = "root";
	private static String passwords = "20130334";
	private static Connection jdbcConnection;

	public static Connection GetConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			jdbcConnection = DriverManager
					.getConnection(dbURL, username, passwords);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jdbcConnection;

	}
}
