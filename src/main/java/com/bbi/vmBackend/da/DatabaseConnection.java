package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	Statement stmt = null;
	Connection conn = null;
	PreparedStatement preparedStmt;

	public  Connection GetConnection() {
		Connection conn = null;
		String URL = "jdbc:mysql://localhost/vm";
		String USER = "root";
		String PASS = "admin";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error in connection");
		}
		System.out.println("Successfully connected");
		return conn;
	}
}
