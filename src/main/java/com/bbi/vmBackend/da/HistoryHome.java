package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.History;

public class HistoryHome extends DBConnection implements DaoHome {

	
	String sql;

	@Override
	public List<DaoObject> listAll() {
		sql = "SELECT * FROM history ";
		Connection conn = GetConnection();
		List<DaoObject> HistoryList = new ArrayList<>();
		History history = new History();
		ResultSet rs;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			rs = preparedStmt.executeQuery();

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

	@Override
	public DaoObject getById(DaoObject obj) {
		sql = "SELECT * FROM history " + "WHERE id=?";
		Connection conn = GetConnection();
		History history = (History) obj;
		ResultSet rs;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, history.getId());

			rs = preparedStmt.executeQuery();
			while (rs.next()) {

				history.setId(rs.getInt("id"));
				history.setUserType(rs.getString("user_type"));
				history.setDate(rs.getDate("date"));
				history.setName(rs.getString("name"));
				history.setHistory_UserId(rs.getInt("history_user_id"));
				history.setHistory_RequestId(rs.getInt("history_request_id"));
				history.setStatus(rs.getString("status"));

				rs.close();
				conn.close();

			}
		} catch (SQLException sq) {
		}
		System.out.println("Error in selecting the required id !");
		return history;
	}

	@Override
	public boolean insert(DaoObject obj) {
		boolean entered = false;

		History history = (History) obj;
		sql = "INSERT INTO history user_type , date , name , history_user_id , "
				+ "history_request_id , status VALUES (?,?,?,?,?,?,?)";
		try {

			Connection conn = GetConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, history.getUserType());
			preparedStmt.setDate(2, (Date) history.getDate());
			preparedStmt.setString(3, history.getName());
			preparedStmt.setInt(4, history.getHistory_UserId());
			preparedStmt.setInt(5, history.getHistory_RequestId());
			preparedStmt.setString(6, history.getStatus());

			preparedStmt.executeUpdate();
			conn.close();
			entered = true;

		} catch (SQLException sq) {
			System.out.println("Error during inserting history ! ");
			entered = false;
		}
		return entered;
	}

	@Override
	public boolean update(DaoObject obj) {
		boolean entered = false;
		sql = "UPDATE history set user_type=? , date=? , name=? , history_user_id=? ,history_request_id=? , status=? "
				+ " WHERE id=?";
		Connection conn = GetConnection();
		History history = (History) obj;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, history.getUserType());
			preparedStmt.setDate(2, (Date) history.getDate());
			preparedStmt.setString(3, history.getName());
			preparedStmt.setInt(4, history.getHistory_UserId());
			preparedStmt.setInt(5, history.getHistory_RequestId());
			preparedStmt.setString(6, history.getStatus());
			preparedStmt.executeUpdate();
			conn.close();
			entered = true;
		} catch (SQLException sq) {
			System.out.println("Error in updatting");
			entered = false;
		}

		return entered;
	}

	@Override
	public boolean delete(DaoObject obj) {
		sql = "DELETE FROM history " + "WHERE id = ?";
		Connection conn = GetConnection();
		boolean entered = false;
		History history = (History) obj;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, history.getId());
			preparedStmt.executeUpdate();
			conn.close();
			entered = true;

		} catch (SQLException sq) {
			System.out.println("Error during deleting");
			entered = false;
		}
		return entered;
	}

	public List<DaoObject> EmployeeHistory(DaoObject obj) {
		sql = "SELECT * FROM history " + "WHERE history_user_id=?";
		Connection conn = GetConnection();
		History history = (History) obj;
		List<DaoObject> EmployeeHistory = new ArrayList<>();
		ResultSet rs;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, history.getHistory_UserId());
			rs = preparedStmt.executeQuery();
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

		} catch (SQLException sq) {
			System.out.println("Error in selecting the required id !");

		}
		return EmployeeHistory;
	}

}