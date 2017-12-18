package com.bbi.vmBackend.da;

import java.util.List;

import com.bbi.vmBackend.da.dao.Request;

public interface RequestDaoHome {
	public List<Request> listAll();

	public Request getById(int id);

	public boolean insert(Request obj);

	public boolean update(Request obj);

	public boolean delete(Request obj);
}
