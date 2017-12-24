package com.bbi.vmBackend.businessLogic;

import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Session;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class Authentication {
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public Session authenticate(String userName, String password) { // get employee info from facade
		Employee employee = dataAccessFacade.facadeEmplyeeExist(userName, password); // get authorization

		Session session = new Session();
		if (employee != null){
			return dataAccessFacade.facadeGetSession(employee);
		}
		else
			return null;

	}
	public int validateSession(Session session) {
		int nextToken = -1;
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		
		if ((Session) dataAccessFacade.getById(session) != null) {   //session created
			nextToken = session.getToken()+1;
			session.setToken(nextToken);
			if (!dataAccessFacade.update(session)){
				nextToken  = -1;
			}			
		}

		
	 return nextToken;
	}


}
