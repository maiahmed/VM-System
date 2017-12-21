package com.bbi.vmBackend.businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbi.vmBackend.da.DBConnection;
import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Request;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class EmployeeImpl extends DBConnection {

	private final static String USERINFOQUERY = "SELECT name , password FROM employee " + "Where name=? , password=?";
	private static Map<String, String> basicEmplInfo;
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public static Map<String, String> getBasicEmplInfo() {
		return basicEmplInfo;
	}

	public Map<String, String> loginInfo(String userName, String password) {
		Employee employee = new Employee();
		basicEmplInfo = new HashMap<>();
		Connection conn = getConnection();
		ResultSet rs;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(USERINFOQUERY);
			preparedStmt.setString(1, employee.getName());
			preparedStmt.setString(2, employee.getPassword());
			rs = preparedStmt.executeQuery();

			while (rs.next()) {
				basicEmplInfo.put("userId", rs.getInt("user_id") + "");
				basicEmplInfo.put("managerId", rs.getString("manager"));
				basicEmplInfo.put("type", rs.getString("type"));

			}

		} catch (Exception e) {
			System.out.println("Error in employee info !");
		}
		return basicEmplInfo;
	}

	public boolean addNewEmployee(Employee employee) { // done by manager
		String loginUserId = basicEmplInfo.get("userId");
		String loginManagerId = basicEmplInfo.get("managerId");

		if (loginUserId.equals(loginManagerId)) {
			dataAccessFacade.insert(employee);
		}
		return false;
	}

	public List<DaoObject> employeeNotifications() { // get notifications of current employee
		String loginUserId = basicEmplInfo.get("userId");
		return dataAccessFacade.EmployeeNotification(loginUserId);
	}

	public List<Request> EmployeeRequests(int employeeId) {
		String type = basicEmplInfo.get("type");
		if (type.equals("manager") || type.equals("IT")) { // Get the requests that hasn't approval yet
			// take get requests by type from mai
			// return ;
		}
		return null; // will be removed
	}

	public List<DaoObject> listAllEmplBL() {
		return dataAccessFacade.listAll(2); // list all notification

	}

	public boolean insertEmplBL(DaoObject obj) {
		if (dataAccessFacade.insert(obj))
			return true;
		else
			return false;

	}

	public boolean updateEmplBL(DaoObject obj) {
		if (dataAccessFacade.update(obj))
			return true;
		else
			return false;
	}

	public boolean deleteEmplBL(DaoObject obj) {
		if (dataAccessFacade.delete(obj))
			return true;
		else
			return false;
	}

	public DaoObject getEmplByIdBL(DaoObject obj) {
		return dataAccessFacade.getById(obj);

	}

}