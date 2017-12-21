package com.bbi.vmBackend.facade;

import java.util.List;

import com.bbi.vmBackend.da.CommentHome;
import com.bbi.vmBackend.da.EmployeeHome;
import com.bbi.vmBackend.da.HistoryHome;
import com.bbi.vmBackend.da.HostHome;
import com.bbi.vmBackend.da.NotificationsHome;
import com.bbi.vmBackend.da.OSHome;
import com.bbi.vmBackend.da.RequestHome;
import com.bbi.vmBackend.da.SessionHome;
import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Session;

public class DataAccessFacade {
	private CommentHome commentHome;
	private EmployeeHome employeeHome;
	private HistoryHome historyHome;
	private HostHome hostHome;
	private NotificationsHome notificationsHome;
	private OSHome osHome;
	private RequestHome requestHome;
	private SessionHome sessionHome;

	public DataAccessFacade() {
		commentHome = new CommentHome();
		employeeHome = new EmployeeHome();
		historyHome = new HistoryHome();
		hostHome = new HostHome();
		notificationsHome = new NotificationsHome();
		osHome = new OSHome();
		requestHome = new RequestHome();
		sessionHome = new SessionHome();

	}

	public Employee facadeEmplyeeExist(String userName, String password) {
		return employeeHome.getEmployee(userName, password);
	}

	public Session facadeGetSession(Employee employee) {
		return employeeHome.createSession(employee);
	}

	public List<DaoObject> listAll(int type) {

		if (type == 1)
			return commentHome.listAll();
		else if (type == 2)
			return employeeHome.listAll();
		else if (type == 3)
			return historyHome.listAll();
		else if (type == 4)
			return hostHome.listAll();
		else if (type == 5)
			return notificationsHome.listAll();
		else if (type == 6)
			return osHome.listAll();
		else if (type == 7)
			return requestHome.listAll();
		else if (type == 8)
			return sessionHome.listAll();

		else
			return null;

	}

	public DaoObject getById(DaoObject obj) {
		if (obj instanceof CommentHome)
			return commentHome.getById(obj);
		else if (obj instanceof EmployeeHome)
			return employeeHome.getById(obj);
		else if (obj instanceof HistoryHome)
			return historyHome.getById(obj);
		else if (obj instanceof HostHome)
			return hostHome.getById(obj);
		else if (obj instanceof NotificationsHome)
			return notificationsHome.getById(obj);
		else if (obj instanceof OSHome)
			return osHome.getById(obj);
		else if (obj instanceof RequestHome)
			return requestHome.getById(obj);
		else if (obj instanceof SessionHome)
			return sessionHome.getById(obj);

		else
			return null;

	}

	public List<DaoObject> EmployeeNotification(String loginUserId) {
		return EmployeeNotification(loginUserId);

	}

	public boolean insert(DaoObject obj) {
		if (obj instanceof CommentHome)
			return commentHome.insert(obj);
		else if (obj instanceof EmployeeHome)
			return employeeHome.insert(obj);
		else if (obj instanceof HistoryHome)
			return historyHome.insert(obj);
		else if (obj instanceof HostHome)
			return hostHome.insert(obj);
		else if (obj instanceof NotificationsHome)
			return notificationsHome.insert(obj);
		else if (obj instanceof OSHome)
			return osHome.insert(obj);
		else if (obj instanceof RequestHome)
			return requestHome.insert(obj);
		else if (obj instanceof SessionHome)
			return sessionHome.insert(obj);

		else
			return false;

	}

	public boolean update(DaoObject obj) {
		if (obj instanceof CommentHome)
			return commentHome.update(obj);
		else if (obj instanceof EmployeeHome)
			return employeeHome.update(obj);
		else if (obj instanceof HistoryHome)
			return historyHome.update(obj);
		else if (obj instanceof HostHome)
			return hostHome.update(obj);
		else if (obj instanceof NotificationsHome)
			return notificationsHome.update(obj);
		else if (obj instanceof OSHome)
			return osHome.update(obj);
		else if (obj instanceof RequestHome)
			return requestHome.update(obj);
		else if (obj instanceof SessionHome)
			return sessionHome.update(obj);

		else
			return false;

	}

	public boolean delete(DaoObject obj) {
		if (obj instanceof CommentHome)
			return commentHome.delete(obj);
		else if (obj instanceof EmployeeHome)
			return employeeHome.delete(obj);
		else if (obj instanceof HistoryHome)
			return historyHome.delete(obj);
		else if (obj instanceof HostHome)
			return hostHome.delete(obj);
		else if (obj instanceof NotificationsHome)
			return notificationsHome.delete(obj);
		else if (obj instanceof OSHome)
			return osHome.delete(obj);
		else if (obj instanceof RequestHome)
			return requestHome.delete(obj);
		else if (obj instanceof SessionHome)
			return sessionHome.delete(obj);

		else
			return false;

	}


}
