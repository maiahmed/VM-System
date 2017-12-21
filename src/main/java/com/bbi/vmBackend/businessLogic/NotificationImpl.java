package com.bbi.vmBackend.businessLogic;

import com.bbi.vmBackend.da.dao.Notifications;
import com.bbi.vmBackend.da.dao.Session;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class NotificationImpl {

	public void insert(Notifications notification) {
		// TODO Auto-generated method stub

	}

	public int getSession(Session session) {
		int nextToken = -1;
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		
		if ((Session) dataAccessFacade.getById(session) != null) {
			nextToken = session.getToken()+1;
			session.setToken(nextToken);
			if (dataAccessFacade.update(session)){
			}			
		}

		
	 return nextToken;
	}

}
