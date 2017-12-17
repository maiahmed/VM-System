package com.bbi.rest.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbi.rest.DataAccess.Doa.Employee;

public class EmployeeHome {

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

	// reminder : the id of the employee is auto increment
	public void InsertNewEmployee(Employee employee) {
		sql = "INSERT INTO employee (name, email, password, type, extra_impl, manager) " + "VALUES (?,?,?,?,?,?)";
		try {

			conn = GetConnection();
			PreparedStmt = conn.prepareStatement(sql);
			PreparedStmt.setString(1, employee.getName());
			PreparedStmt.setString(2, employee.getEmail());
			PreparedStmt.setString(3, employee.getPassword());
			PreparedStmt.setString(4, employee.getType());
			PreparedStmt.setString(5, employee.getExtraEmpl());
			PreparedStmt.setInt(6, employee.getManager());

			PreparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in Employee inserting function ");
		}
	}

	public void Deleting(int EmplId) {
		sql = "DELETE FROM employee " + "WHERE user_id = ?";
		conn = GetConnection();
		Employee employee = new Employee();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			if (employee.getUserId() == EmplId) {
				PreparedStmt.setInt(1, EmplId);
				PreparedStmt.executeUpdate();
				conn.close();
			} else {
				System.out.println("Id doesn't extist or deleted before !");
			}
		} catch (SQLException sq) {
			System.out.println("Error in deleting the selected employee");
		}
	}

	public void Updating(Employee employee) {
		sql = "UPDATE employee set name=? ,email=? , password=? , type=? , extra_impl=? , manager=?"
				+ " WHERE user_id=?";
		conn = GetConnection();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			PreparedStmt.setString(1, employee.getName());
			PreparedStmt.setString(2, employee.getEmail());
			PreparedStmt.setString(3, employee.getPassword());
			PreparedStmt.setString(4, employee.getType());
			PreparedStmt.setString(5, employee.getExtraEmpl());
			PreparedStmt.setInt(6, employee.getManager());
			PreparedStmt.setInt(7, employee.getUserId());
			PreparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in updating emplyee data !");
		}
	}

	public List<Employee> SelectAll() {
		sql = "SELECT * FROM employee";
		conn = GetConnection();
		ResultSet rs;
		List<Employee> EmployeesList = new ArrayList<>();
		Employee employee = new Employee();

		try {
			PreparedStmt = conn.prepareStatement(sql);
			rs = PreparedStmt.executeQuery();

			while (rs.next()) {

				employee.setUserId(rs.getInt("user_id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setType(rs.getString("type"));
				employee.setExtraEmpl(rs.getString("extra_impl"));
				employee.setManager(rs.getInt("manager"));

				EmployeesList.add(employee);
				System.out.println("");
			}
			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return EmployeesList;
	}

	public Employee SelectOne(int employeeId) {
		sql = "SELECT * FROM employee " + "Where user_id=?";
		conn = GetConnection();
		ResultSet rs;
		Employee employee = new Employee();

		try {
			PreparedStmt = conn.prepareStatement(sql);
				PreparedStmt.setInt(1, employee.getUserId());
				rs = PreparedStmt.executeQuery();

				while (rs.next()) {
					if (employee.getUserId() == employeeId) {

					employee.setName(rs.getString("name"));
					employee.setEmail(rs.getString("email"));
					employee.setPassword(rs.getString("password"));
					employee.setType(rs.getString("type"));
					employee.setExtraEmpl(rs.getString("extra_impl"));
					employee.setManager(rs.getInt("manager"));

					System.out.println("");
				
				rs.close();
				conn.close();
			} else {
				System.out.println("The selected id doesn't exist !");
			}}
		} catch (SQLException sq) {
			System.out.println("Error in selecting the required employee !");
		}
		return employee;
	}

}