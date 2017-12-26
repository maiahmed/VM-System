package com.bbi.vmBackend.businessLogic;

import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Session;
import com.bbi.vmBackend.facade.DataAccessFacade;
<<<<<<< HEAD

public class Authentication {  //into business logic
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public Session authenticate(String userName, String password) { // get employee info from facade
		Employee employee = dataAccessFacade.checkAuthentication(userName, password); // get authorization
		
=======
//1448
public class Authentication {
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public Session authenticate(String userName, String password) { // get employee info from facade
		Employee employee = dataAccessFacade.facadeEmplyeeExist(userName, password); // get authorization

>>>>>>> d3122d35e7991d92e1eeb6a3ea543170c85d7cb1
		Session session = new Session();
		if (employee != null){
			session = dataAccessFacade.facadeGetSession(employee);
			System.out.println("++++++++++++ "+session.getSessionId());
			return session;
		}
		else
			return null;

	}
	
	public int validateSession(Session session) {
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
