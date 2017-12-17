package com.bbi.rest.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbi.rest.DataAccess.Doa.History;

public class HistoryHome {

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

	public void InsertNewHistory(History history) {
		sql = "INSERT INTO history user_type , date , name , history_user_id , "
				+ "history_request_id , status VALUES (?,?,?,?,?,?,?)";
		try {

			conn = GetConnection();
			PreparedStmt = conn.prepareStatement(sql);
			PreparedStmt.setString(1, history.getUserType());
			PreparedStmt.setDate(2, history.getDate());
			PreparedStmt.setString(3, history.getName());
			PreparedStmt.setInt(4, history.getHistory_UserId());
			PreparedStmt.setInt(5, history.getHistory_RequestId());
			PreparedStmt.setString(6, history.getStatus());

			PreparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error during inserting history ! ");
		}
	}

	public void Deleting(int HistoryId) {
		sql = "DELETE FROM history " + "WHERE id = ?";
		conn = GetConnection();
		History history = new History();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			if (HistoryId == history.getId()) {
				PreparedStmt.setInt(1, HistoryId);
				PreparedStmt.executeUpdate();
				conn.close();
			} else {
				System.out.println("The id doesn't exist or deleted before !");
			}
		} catch (SQLException sq) {
			System.out.println("Error during deleting");
		}
	}

	public void Updating(History history) {
		sql = "UPDATE history set user_type=? , date=? , name=? , history_user_id=? ,history_request_id=? , status=? "
				+ " WHERE id=?";
		conn = GetConnection();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			PreparedStmt.setString(1, history.getUserType());
			PreparedStmt.setDate(2, history.getDate());
			PreparedStmt.setString(3, history.getName());
			PreparedStmt.setInt(4, history.getHistory_UserId());
			PreparedStmt.setInt(5, history.getHistory_RequestId());
			PreparedStmt.setString(6, history.getStatus());
			PreparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in updatting");
		}
	}

	public List<History> SelectAll() {
		sql = "SELECT * FROM history ";
		conn = GetConnection();
		List<History> HistoryList = new ArrayList<>();
		History history = new History();
		ResultSet rs;
		try {
			PreparedStmt = conn.prepareStatement(sql);
			rs = PreparedStmt.executeQuery();

			while (rs.next()) {
				history.setId(rs.getInt("id"));
				history.setUserType(rs.getString("user_type"));
				history.setDate(rs.getDate("date"));
				history.setName(rs.getString("name"));
				history.setHistory_UserId(rs.getInt("history_user_id"));
				history.setHistory_RequestId(rs.getInt("history_request_id"));
				history.setStatus(rs.getString("status"));
				HistoryList.add(history);
			}
			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return HistoryList;
	}

	public History SelectOne(int historyId) {
		sql = "SELECT * FROM history " + "WHERE id=?";
		conn = GetConnection();
		History history = new History();
		ResultSet rs;
		try {
			PreparedStmt = conn.prepareStatement(sql);
			rs = PreparedStmt.executeQuery();
			while (rs.next()) {
				if (history.getId() == historyId) {
					history.setId(rs.getInt("id"));
					history.setUserType(rs.getString("user_type"));
					history.setDate(rs.getDate("date"));
					history.setName(rs.getString("name"));
					history.setHistory_UserId(rs.getInt("history_user_id"));
					history.setHistory_RequestId(rs.getInt("history_request_id"));
					history.setStatus(rs.getString("status"));

					rs.close();
					conn.close();
				} else {
					System.out.println("The id doesn't exist or deleted before !");
				}
			}
		} catch (SQLException sq) {
		}
		System.out.println("Error in selecting the required id !");
		return history;
	}

	public List<History> EmployeeHistory(int user_ID) {
		sql = "SELECT * FROM history " + "WHERE history_user_id=?";
		conn = GetConnection();
		History history = new History();
		List<History> EmployeeHistory = new ArrayList<>();
		ResultSet rs;
		try {
			PreparedStmt = conn.prepareStatement(sql);
			if (history.getHistory_UserId() == user_ID) {
				PreparedStmt.setInt(1, user_ID);
				rs = PreparedStmt.executeQuery();
				while (rs.next()) {
					history.setId(rs.getInt("id"));
					history.setUserType(rs.getString("user_type"));
					history.setDate(rs.getDate("date"));
					history.setName(rs.getString("name"));
					history.setHistory_UserId(rs.getInt("history_user_id"));
					history.setHistory_RequestId(rs.getInt("history_request_id"));
					history.setStatus(rs.getString("status"));
					EmployeeHistory.add(history);
					rs.close();
					conn.close();
				}
			} else {
				System.out.println("The id doesn't exist or deleted before !");

			}
		} catch (SQLException sq) {
			System.out.println("Error in selecting the required id !");

		}
		return EmployeeHistory;
	}

}
