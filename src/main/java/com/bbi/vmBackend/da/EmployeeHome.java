package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.*;
import com.mysql.jdbc.Statement;
import com.sun.xml.bind.v2.runtime.Name;

public class EmployeeHome extends DBConnection implements DaoHome {
	private final static String INSERTQUERY = "INSERT INTO employee (name, email, password, type, extra_impl, manager) "
			+ "VALUES (?,?,?,?,?,?)";
	private final static String SELECTQUERY = "SELECT * FROM employee";
	private final static String UPDATEQUERY = "UPDATE employee set name=? ,email=? , password=? , type=? , extra_impl=? , manager=?"
			+ " WHERE user_id=?";
	private final static String DELETEQUERY = "DELETE FROM employee "
			+ "WHERE user_id = ?";
	private final static String GETONEQUERY = "SELECT * FROM employee "
			+ "Where user_id=?";
	private final static String GETEMPLOYEEINFO = "SELECT user_id , name , email , type , manager FROM employee where name=? and password = ?";
	private final static String INSERTNEWSESSION = "INSERT INTO session (user_Id, token,  session.key, lastInsertion, lastUpdate) values (?,?,?,?,?)";

	public Session createSession(DaoObject obj) {

		Session session = new Session();
		Employee employee = (Employee) obj;
		System.out.println(employee.getUserId());
		session.setKey("ABC"); // will increase each char by 3;
		session.setUser_Id(employee.getUserId());
		session.setToken(employee.getUserId()); // will increase 1
		
		// session.setLastInsertion());
		// session.setLastUpdate(lastUpdate);

		try {
			Connection conn = getConnection();
			PreparedStatement preparedStmt = conn
					.prepareStatement(INSERTNEWSESSION, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setInt(1, session.getUser_Id());
			preparedStmt.setInt(2, session.getToken());
			preparedStmt.setString(3, session.getKey());
			preparedStmt.setDate(4, (Date) session.getLastInsertion());
			preparedStmt.setDate(5, (Date) session.getLastUpdate());
			
			System.out.println("SSS " + preparedStmt);
			preparedStmt.executeUpdate() ;
			System.out.println("---------------------");
			ResultSet rs = preparedStmt.getGeneratedKeys();
			System.out.println("---------------------");
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
				  session.setSessionId(generatedKey);

			}
			System.out.println("Inserted record's ID: " + generatedKey);

//			if (rs.next()) {
//				  System.out.println("npooooooooooo ");
//
//			  int newId = rs.getInt("sessionId");
//			  session.setSessionId(newId);
//			  System.out.println("new "+newId);
//			}
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in creating new session function ");
		}
		return session;
	}

	public Employee getEmployee(String userName, String password) {
		Connection conn = getConnection();
		System.out.println("---------I get Connection-------------" + userName
				+ " " + password);
		Employee employee = new Employee();
		ResultSet rs;

		try {
			PreparedStatement preparedStmt = conn
					.prepareStatement(GETEMPLOYEEINFO);
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, password);
			rs = preparedStmt.executeQuery();
			if (!rs.next()) {
				employee = null;
				System.out.println("no data");
			}

			else {
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

	@Override
	public List<DaoObject> listAll() {

		Connection conn = getConnection();

		ResultSet rs;
		List<DaoObject> EmployeesList = new ArrayList<>();
		Employee employee = new Employee();

		try {
			PreparedStatement preparedStmt = conn.prepareStatement(SELECTQUERY);
			rs = preparedStmt.executeQuery();

			while (rs.next()) {

				employee.setUserId(rs.getInt("user_id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setType(rs.getString("type"));
				employee.setExtraEmpl(rs.getString("extra_impl"));
				employee.setManager(rs.getInt("manager"));

				EmployeesList.add((DaoObject) employee);
			}
			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return EmployeesList;
	}

	@Override
	public DaoObject getById(DaoObject obj) {

		Connection conn = getConnection();
		ResultSet rs;
		Employee employee = (Employee) obj;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(GETONEQUERY);
			preparedStmt.setInt(1, employee.getUserId());
			rs = preparedStmt.executeQuery();

			while (rs.next()) {
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setType(rs.getString("type"));
				employee.setExtraEmpl(rs.getString("extra_impl"));
				employee.setManager(rs.getInt("manager"));
				rs.close();
				conn.close();
			}
		} catch (SQLException sq) {
			System.out.println("Error in selecting the required employee !");
		}
		return employee;
	}

	@Override
	public boolean insert(DaoObject obj) {
		Employee employee = (Employee) obj;
		boolean entered = false;
		try {

			Connection conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(INSERTQUERY);
			preparedStmt.setString(1, employee.getName());
			preparedStmt.setString(2, employee.getEmail());
			preparedStmt.setString(3, employee.getPassword());
			preparedStmt.setString(4, employee.getType());
			preparedStmt.setString(5, employee.getExtraEmpl());
			preparedStmt.setInt(6, employee.getManager());
			entered = preparedStmt.executeUpdate() > 0;
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in Employee inserting function ");
		}
		return entered;
	}

	@Override
	public boolean update(DaoObject obj) {

		Connection conn = getConnection();
		boolean entered = false;
		Employee employee = (Employee) obj;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(UPDATEQUERY);
			preparedStmt.setString(1, employee.getName());
			preparedStmt.setString(2, employee.getEmail());
			preparedStmt.setString(3, employee.getPassword());
			preparedStmt.setString(4, employee.getType());
			preparedStmt.setString(5, employee.getExtraEmpl());
			preparedStmt.setInt(6, employee.getManager());
			preparedStmt.setInt(7, employee.getUserId());
			entered = preparedStmt.executeUpdate() > 0;
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in updating emplyee data !");
		}
		return entered;
	}

	@Override
	public boolean delete(DaoObject obj) {

		Connection conn = getConnection();
		Employee employee = (Employee) obj;
		boolean entered = false;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(DELETEQUERY);
			preparedStmt.setInt(1, employee.getUserId());
			entered = preparedStmt.executeUpdate() > 0;
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in deleting the selected employee");
		}
		return entered;
	}

}