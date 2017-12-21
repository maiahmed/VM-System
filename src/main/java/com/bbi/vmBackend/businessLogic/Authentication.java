package com.bbi.vmBackend.businessLogic;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import com.bbi.vmBackend.da.EmployeeHome;
import com.bbi.vmBackend.da.dao.Employee;

public class Authentication {

	public boolean isUserAuthenticated(String userName, String password) {
		EmployeeHome employeeHome = new EmployeeHome();
		Session session = new Session();
		
		Employee employee = employeeHome.getEmployee(userName, password);
		if (employee != null) {
			Session sessionId = session.insertSession(employee.getUserId());
			return true;
		} else
			return false;

	}

}
