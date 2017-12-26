package com.bbi.vmBackend.businessLogic;

import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class HistoryBL {
	
	DataAccessFacade dataAccessFacade = new DataAccessFacade();
	
	public List<DaoObject> listAllHisBL() {
		return dataAccessFacade.listAll(3); // list all notification

	}
	public DaoObject getHisByIdBL(DaoObject obj) {
		return dataAccessFacade.getById(obj);

	}
	//history contains notifications , insert new employee , delete employee , comments , requests
	public boolean insertHisBL(DaoObject obj) {
		if (dataAccessFacade.insert(obj))
			return true;
		else
			return false;

	}

//	public boolean updateHisBL(DaoObject obj) {
//		if (dataAccessFacade.update(obj))
//			return true;
//		else
//			return false;
//	}

//	public boolean deleteHisBL(DaoObject obj) {
//		if (dataAccessFacade.delete(obj))
//			return true;
//		else
//			return false;
//	}

	
}
