package com.bbi.vmBackend.da;

import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;

public interface DaoHome {

	public List<DaoObject> listAll();

	public DaoObject getById(DaoObject obj);

	public boolean insert(DaoObject obj);

	public boolean update(DaoObject obj);

	public boolean delete(DaoObject obj);

}
