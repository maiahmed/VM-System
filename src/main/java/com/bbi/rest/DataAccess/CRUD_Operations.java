package com.bbi.rest.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CRUD_Operations {
		String sql;
		Statement stmt = null;
		Connection conn = null;
		PreparedStatement PreparedStmt;

		public static Connection GetConnection() {
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


		public void InsertNewEmployee(String NewEmployee) {
			sql = "INSERT INTO employee (name) VALUES (?)";
			try {

				conn = GetConnection();
				PreparedStmt = conn.prepareStatement(sql);
				PreparedStmt.setString(1, NewEmployee);
				PreparedStmt.executeUpdate();
				conn.close();

			} catch (SQLException sq) {
				System.out.println("Error in inserting function ");
			}
		}

		public void Deleting() {
			sql = "DELETE FROM employee " + "WHERE id = ?";
			conn = GetConnection();
			try {
				PreparedStmt = conn.prepareStatement(sql);
				PreparedStmt.setInt(1, 3);
				PreparedStmt.executeUpdate();
				conn.close();

			} catch (SQLException sq) {
				System.out.println("Error in deletion");
			}
		}

		public void Updating(int EmployeeId) {
			sql = "UPDATE employee set name=? WHERE id=?";
			conn = GetConnection();
			try {
				PreparedStmt = conn.prepareStatement(sql);
				PreparedStmt.setString(1, "Hello");
				PreparedStmt.setInt(2, EmployeeId);
				PreparedStmt.executeUpdate();
				conn.close();

			} catch (SQLException sq) {
				System.out.println("Error in updatting");
			}
		}

		public void Selection() {
			sql = "SELECT * FROM employee ";
			conn = GetConnection();
			ResultSet rs;
			try {
				PreparedStmt = conn.prepareStatement(sql);
				rs = PreparedStmt.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					System.out.print("ID: " + id);
					System.out.print(", name: " + name);
					System.out.println("");
				}
				rs.close();
				conn.close();
			} catch (SQLException sq) {
				System.out.println("Error in selection");
			}
		}

	}



