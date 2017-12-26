package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Session;

public class SessionHome extends SingletonDBConnection implements DaoHome {

	private final static String INSERTQUERY = "INSERT INTO session (user_id ,token , key , lastInsertion , lastUpdate ) "
			+ "VALUES (?,?,?,?,?)";
	private final static String SELECTQUERY = "SELECT * FROM session";
	private final static String UPDATEQUERY = "UPDATE session set token=? , session.key=? , lastInsertion=? , lastUpdate=?"
			+ " WHERE user_id=?";
	private final static String DELETEQUERY = "DELETE FROM session " + "WHERE user_id = ?";
	private final static String GETONEQUERY = "SELECT * FROM session Where sessionid=?";

	@Override
	public List<DaoObject> listAll() {
		Connection conn = getConnection();

		ResultSet rs;
		List<DaoObject> sessionsList = new ArrayList<>();
		Session session = new Session();

		try {
			PreparedStatement preparedStmt = conn.prepareStatement(SELECTQUERY);
			rs = preparedStmt.executeQuery();

			while (rs.next()) {

				session.setUser_Id(rs.getInt("user_id"));
				session.setToken(rs.getInt("token"));
				session.setKey(rs.getString("key"));
				session.setLastInsertion(rs.getDate("lastInsertion"));
				session.setLastUpdate(rs.getDate("lastUpdate"));

				sessionsList.add((DaoObject) session);
				System.out.println("");
			}
			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return sessionsList;
	}

	@Override
	public DaoObject getById(DaoObject obj) {
		// private final static String GETONEQUERY = "SELECT * FROM session " + "Where
		// user_id=?";

		
		Connection conn = getConnection();
		ResultSet rs;
		Session session = (Session) obj;
		System.out.println("-----ana f session getId" + session.getUser_Id()+" sss");

		try {
			PreparedStatement preparedStmt = conn.prepareStatement(GETONEQUERY);
			preparedStmt.setInt(1, session.getUser_Id());
			System.out.println(preparedStmt);

			rs = preparedStmt.executeQuery();
			
			if(!rs.next()){
				System.out.println("no Datae");
			}
			else{

				session.setUser_Id(rs.getInt("user_id"));
				session.setToken(rs.getInt("token"));
				session.setKey(rs.getString("key"));
				session.setLastInsertion(rs.getDate("lastInsertion"));
				session.setLastUpdate(rs.getDate("lastUpdate"));
				rs.close();
				conn.close();
			}
		} catch (SQLException sq) {
			System.out.println("Error in selecting the required session !");
		}
		return session;
	}

	@Override
	public boolean insert(DaoObject obj) {
		// private final static String INSERTQUERY = "INSERT INTO session (user_id
		// ,token , key , lastInsertion , lastUpdate ) "
		// + "VALUES (?,?,?,?,?)";
		Session session = (Session) obj;
		boolean entered = false;
		try {

			Connection conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(INSERTQUERY);
			preparedStmt.setInt(1, session.getUser_Id());
			preparedStmt.setInt(2, session.getToken());
			preparedStmt.setString(3, session.getKey());
			preparedStmt.setDate(4, (Date) session.getLastInsertion());
			preparedStmt.setDate(5, (Date) session.getLastUpdate());
			// preparedStmt.executeUpdate();
			entered = preparedStmt.executeUpdate() > 0;
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in session inserting function ");
		}
		return entered;
	}

	@Override
	public boolean update(DaoObject obj) {
		// private final static String UPDATEQUERY = "UPDATE session set token=? , key=?
		// , lastInsertion=? , lastUpdate=?"
		// + " WHERE user_id=?";
		Connection conn = getConnection();
		boolean entered = false;
		Session session = (Session) obj;
		System.out.println(" -=-=-=->>> "+session.getUser_Id()+" "+session.getToken());

		try {
			PreparedStatement preparedStmt = conn.prepareStatement(UPDATEQUERY);
			preparedStmt.setInt(1, session.getToken());
			preparedStmt.setString(2, session.getKey());
			preparedStmt.setDate(3, (Date) session.getLastInsertion());
			preparedStmt.setDate(4, (Date) session.getLastUpdate());
			preparedStmt.setInt(5, session.getUser_Id());
			System.out.println("== "+preparedStmt);
			entered = preparedStmt.executeUpdate() > 0;
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in updating session data !");
		}
		return entered;
	}

	@Override
	public boolean delete(DaoObject obj) {
		// private final static String DELETEQUERY = "DELETE FROM session " + "WHERE
		// user_id = ?";

		Connection conn = getConnection();
		Session session = (Session) obj;
		boolean entered = false;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(DELETEQUERY);
			preparedStmt.setInt(1, session.getUser_Id());
			entered = preparedStmt.executeUpdate() > 0;
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in deleting the selected employee");
		}
		return entered;
	}



}
