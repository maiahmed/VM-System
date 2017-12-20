package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.*;

public class EmployeeHome extends DBConnection implements DaoHome {
	private final static String INSERTQUERY = "INSERT INTO employee (name, email, password, type, extra_impl, manager) " + "VALUES (?,?,?,?,?,?)";
	private final static String SELECTQUERY = "SELECT * FROM employee";
	private final static String UPDATEQUERY = "UPDATE employee set name=? ,email=? , password=? , type=? , extra_impl=? , manager=?"
			+ " WHERE user_id=?";
	private final static String DELETEQUERY = "DELETE FROM employee " + "WHERE user_id = ?";
	private final static String GETONEQUERY = "SELECT * FROM employee " + "Where user_id=?";

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
				System.out.println("");
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
		return (DaoObject) employee;
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
			preparedStmt.executeUpdate();
			conn.close();
			entered = true;

		} catch (SQLException sq) {
			System.out.println("Error in Employee inserting function ");
			entered = false;
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
			preparedStmt.executeUpdate();
			conn.close();
			entered = true;
		} catch (SQLException sq) {
			System.out.println("Error in updating emplyee data !");
			entered = false;
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
			preparedStmt.executeUpdate();
			conn.close();
			entered = true;
		} catch (SQLException sq) {
			System.out.println("Error in deleting the selected employee");
			entered = false;
		}
		return entered;
	}

}