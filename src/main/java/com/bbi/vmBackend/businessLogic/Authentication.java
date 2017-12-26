package com.bbi.vmBackend.businessLogic;

import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Session;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class Authentication {
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public Session authenticate(String userName, String password) { // get employee info from facade
		Employee employee = dataAccessFacade.checkAuthentication(userName, password); // get authorization

		Session session = new Session();
		if (employee != null){
			session = dataAccessFacade.facadeGetSession(employee);
			System.out.println("++++++++++++ "+session.getSessionId());
			return session;
		}
		else
			return null;

	}
	public int getNextToken(Session session) {
		int nextToken = 0;
		
			nextToken = session.getToken();
			DataAccessFacade dataAccessFacade = new DataAccessFacade();
			if(dataAccessFacade.getById(session) != null) {
				nextToken = session.getToken() + 1;
				session.setToken(nextToken);
			}
			return nextToken;
		
	}

	public int validateSession(Session session) { //you only check the token > rename it to validateToken
		int nextToken = -1;
		
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		System.out.println("ana f validateSession=========="+session.getSessionId()+" "+session.getUser_Id());
		if (dataAccessFacade.getById(session) != null) {   //session created
			System.out.println("===========ana b get bl iddd :D  ============= "+session.getSessionId()+" "+session.getUser_Id());
			nextToken = session.getToken()+1;
			session.setToken(nextToken);
			if (!dataAccessFacade.update(session)){
				nextToken  = -1;
			}			
		}

		
	 return nextToken;
	}


}
