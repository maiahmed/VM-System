package com.bbi.rest.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbi.rest.DataAccess.Doa.OS;

public class OSHome {

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

	public void InsertNewOS(OS os) {
		sql = "INSERT INTO os (name, extra_os, manager) VALUES (?,?,?)";
		try {

			conn = GetConnection();
			PreparedStmt = conn.prepareStatement(sql);
			PreparedStmt.setString(1, os.getOSName());
			PreparedStmt.setString(2, os.getOSExtra_OS());
			PreparedStmt.setInt(3, os.getOSManager());

			PreparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in inserting function ");
		}
	}

	public void Deleting(int OSId) {
		sql = "DELETE FROM os " + "WHERE id = ?";
		conn = GetConnection();
		OS os = new OS();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			if (OSId == os.getOSId()) {
				PreparedStmt.setInt(1, OSId);
				PreparedStmt.executeUpdate();
				conn.close();
			} else {
				System.out.println("The selected id doesn't exist or deleted before !");
			}
		} catch (SQLException sq) {
			System.out.println("Error in deleting the selected id !");
		}
	}

	public void Updating(OS os) {
		sql = "UPDATE os set name=? , extra_os=? , manager=?  WHERE id=?";
		conn = GetConnection();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			PreparedStmt.setString(1, os.getOSName());
			PreparedStmt.setString(2, os.getOSExtra_OS());
			PreparedStmt.setInt(3, os.getOSManager());
			PreparedStmt.setInt(4, os.getOSId());
			PreparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in updating the OS !");
		}
	}

	public List<OS> SelectAll() {
		sql = "SELECT * FROM os ";
		conn = GetConnection();
		ResultSet rs;
		List<OS> OSList = new ArrayList<>();
		OS os = new OS();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			rs = PreparedStmt.executeQuery();

			while (rs.next()) {
				os.setOSId(rs.getInt("id"));
				os.setOSName(rs.getString("name"));
				os.setOSExtra_OS(rs.getString("extra_os"));
				os.setOSManager(rs.getInt("manager"));
				OSList.add(os);

			}
			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return OSList;
	}

	public OS SelectOne(int OSId) {
		sql = "SELECT * FROM os " + "WHERE id=?";
		conn = GetConnection();
		ResultSet rs;
		OS os = new OS();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			rs = PreparedStmt.executeQuery();

			while (rs.next()) {
				if (OSId == os.getOSId()) {

					os.setOSId(rs.getInt("id"));
					os.setOSName(rs.getString("name"));
					os.setOSExtra_OS(rs.getString("extra_os"));
					os.setOSManager(rs.getInt("manager"));

					rs.close();
					conn.close();
				} else {
					System.out.println("The id doesn't exist !");
				}
			}
		} catch (SQLException sq) {
			System.out.println("Error in selecting the required os");
		}
		return os;
	}

}
