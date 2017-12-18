package com.bbi.vmBackend.da;

import java.util.List;

import com.bbi.vmBackend.da.dao.Host;

public interface HostDaoHome {
	public List<Host> listAll();

	public Host getById(int id);

	public boolean insert(Host host);

	public boolean update(Host host);

	public boolean delete(Host host);
}
