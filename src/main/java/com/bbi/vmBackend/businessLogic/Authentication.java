package com.bbi.vmBackend.businessLogic;

<<<<<<< HEAD
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Session;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class Authentication {
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public Session isAuthenticated(String userName, String password) { // get employee info from facade
		Employee employee = dataAccessFacade.facadeEmplyeeExist(userName, password); // get authorization

		if (employee != null)
			return dataAccessFacade.facadeGetSession(employee);
		else
			return null;
	}
}
=======
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
>>>>>>> 0dde1115d285363a0ba96ed74d82072801761ebd
