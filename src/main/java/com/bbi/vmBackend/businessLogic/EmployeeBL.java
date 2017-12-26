package com.bbi.vmBackend.businessLogic;

import java.util.List;
import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class EmployeeBL {

	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	public List<DaoObject> listAllEmployeesBL() {
		return dataAccessFacade.listAll(2); // list all notification

	}

	public boolean insertEmployeesBL(DaoObject obj) {
		if (dataAccessFacade.insert(obj))
			return true;
		else
			return false;

	}

	public boolean updateEmployeesBL(DaoObject obj) {
		if (dataAccessFacade.update(obj))
			return true;
		else
			return false;
	}

	public boolean deleteEmployeesBL(DaoObject obj) {
		if (dataAccessFacade.delete(obj))
			return true;
		else
			return false;
	}

	public DaoObject getEmployeeByIdBL(DaoObject obj) {
		return dataAccessFacade.getById(obj);

	}

}