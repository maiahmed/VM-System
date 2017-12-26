package com.bbi.vmBackend.businessLogic;

import java.util.List;
import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class NotificationBL {

	// we are in the business logic layer
	
	public List<DaoObject> getNotificationsFromFacade(int notifUserId) { // list of notifications
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		return dataAccessFacade.EmployeeNotification(notifUserId + "");

	}
}