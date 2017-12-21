package com.bbi.vmBackend.businessLogic;

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