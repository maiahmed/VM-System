package com.bbi.vmBackend.businessLogic;

import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Session;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class Authentication {
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public Session isAuthenticated(String userName, String password) { // get employee info from facade
		Employee employee = dataAccessFacade.facadeEmplyeeExist(userName, password); // get authorization

<<<<<<< HEAD
	public boolean isUserAuthenticated(String userName, String password) {
		EmployeeHome employeeHome = new EmployeeHome();
		Session session = new Session();
		
		Employee employee = employeeHome.getEmployee(userName, password);
		if (employee != null) {
			session = session.insertSession(employee.getUserId());
			return true;
		} else
			return false;
=======
		if (employee != null)
			return dataAccessFacade.facadeGetSession(employee);
		else
			return null;
>>>>>>> 84eb3b53746c666b2b48f5e56045f8027c3c11b8

	}

}
