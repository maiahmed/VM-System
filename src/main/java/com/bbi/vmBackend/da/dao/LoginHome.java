package com.bbi.vmBackend.da.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bbi.vmBackend.da.DBConnection;

public class LoginHome extends DBConnection {

	private final static String GETEMPLOYEEINFO = "SELECT user_id , name , email , type , manager FROM employee where name=? and password = ?";
	private final static String INSERTNEWSESSION = "INSERT INTO session (user_Id, token, key, lastInsertion, lastUpdate)";

	public boolean employeeIsExistOrNot(String userName, String password) {
		Connection conn = getConnection();
		boolean exist;

		try {
			PreparedStatement preparedStmt = conn.prepareStatement(GETEMPLOYEEINFO);
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, password);
			exist = preparedStmt.executeUpdate() > 0;
		} catch (Exception e) {

			exist = false;
		}
		return exist;
	}

	public Employee employeeLoginInfo(String userName, String password) {
		Connection conn = getConnection();
		Employee employee = new Employee();
		ResultSet rs;

		try {
			PreparedStatement preparedStmt = conn.prepareStatement(GETEMPLOYEEINFO);
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, password);
			rs = preparedStmt.executeQuery();

			while (rs.next()) {
				employee.setUserId(rs.getInt("user_id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setType(rs.getString("type"));
				employee.setManager(rs.getInt("manager"));

			}
			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in getEmployee");
		}

		return employee;
	}

	public Session createSession(DaoObject obj) {

		Session session = new Session();
		Employee employee = (Employee) obj;

		session.setKey("ABC"); // will increase each char by 3;
		session.setUser_Id(employee.getUserId());
		session.setToken(employee.getUserId()); // will increase 1
		// session.setLastInsertion());
		// session.setLastUpdate(lastUpdate);

		try {
			Connection conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(INSERTNEWSESSION);
			preparedStmt.setInt(1, session.getUser_Id());
			preparedStmt.setInt(2, session.getToken());
			preparedStmt.setString(3, session.getKey());
			preparedStmt.setDate(4, (Date) session.getLastInsertion());
			preparedStmt.setDate(5, (Date) session.getLastUpdate());
			preparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in creating new session function ");
		}
		return session;
	}
}
