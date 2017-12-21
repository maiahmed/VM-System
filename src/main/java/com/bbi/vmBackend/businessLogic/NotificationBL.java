package com.bbi.vmBackend.businessLogic;

import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class NotificationBL {

	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public List<DaoObject> listAllNotifBL() {
		return dataAccessFacade.listAll(5); // list all notification

	}

	public boolean insertNotifBL(DaoObject obj) {
		if (dataAccessFacade.insert(obj))
			return true;
		else
			return false;

	}

	public boolean updateNotifBL(DaoObject obj) {
		if (dataAccessFacade.update(obj))
			return true;
		else
			return false;
	}

	public boolean deleteNotifBL(DaoObject obj) {
		if (dataAccessFacade.delete(obj))
			return true;
		else
			return false;
	}

	public DaoObject getNotifByIdBL(DaoObject obj) {
		return dataAccessFacade.getById(obj);

	}
}
