package com.bbi.vmBackend.businessLogic;

import java.util.List;
import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.Session;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class NotificationBL {

	// we are in the business logic layer
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

	public List<DaoObject> getNotificationsFromFacade(int notifUserId) { // list of notifications
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		return dataAccessFacade.EmployeeNotification(notifUserId + "");

	}
}