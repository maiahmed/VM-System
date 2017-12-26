package com.bbi.vmBackend.businessLogic;

import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Session;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class Authentication {  //into business logic
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public Session authenticate(String userName, String password) { // get employee info from facade
		Employee employee = dataAccessFacade.checkAuthentication(userName, password); // get authorization
		
		Session session = new Session();
		if (employee != null)
			return dataAccessFacade.facadeGetSession(employee);
		else
			return null;

	}

}
