package com.bbi.vmBackend.businessLogic;

<<<<<<< HEAD
import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class HistoryImpl {

	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public List<DaoObject> listAllNHisfBL() {
		return dataAccessFacade.listAll(3); // list all notification

	}

	public boolean insertHisBL(DaoObject obj) {
		if (dataAccessFacade.insert(obj))
			return true;
		else
			return false;

	}

	public boolean updateHisBL(DaoObject obj) {
		if (dataAccessFacade.update(obj))
			return true;
		else
			return false;
	}

	public boolean deleteHisBL(DaoObject obj) {
		if (dataAccessFacade.delete(obj))
			return true;
		else
			return false;
	}

	public DaoObject getHisByIdBL(DaoObject obj) {
		return dataAccessFacade.getById(obj);

=======
import com.bbi.vmBackend.da.dao.History;

public class HistoryImpl {

	public void insert(History history) {
		// TODO Auto-generated method stub
		
>>>>>>> 0dde1115d285363a0ba96ed74d82072801761ebd
	}

}
