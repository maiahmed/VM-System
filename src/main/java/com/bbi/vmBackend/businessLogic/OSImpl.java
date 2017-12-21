package com.bbi.vmBackend.businessLogic;

import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class OSImpl {
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public List<DaoObject> listAllOSfBL() {
		return dataAccessFacade.listAll(6); // list all notification

	}

	public boolean insertOSBL(DaoObject obj) {
		if (dataAccessFacade.insert(obj))
			return true;
		else
			return false;

	}

	public boolean updateOSBL(DaoObject obj) {
		if (dataAccessFacade.update(obj))
			return true;
		else
			return false;
	}

	public boolean deleteOSBL(DaoObject obj) {
		if (dataAccessFacade.delete(obj))
			return true;
		else
			return false;
	}

	public DaoObject getOSByIdBL(DaoObject obj) {
		return dataAccessFacade.getById(obj);

	}

}