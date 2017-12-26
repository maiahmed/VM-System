package com.bbi.vmBackend.businessLogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.DBConnection;
import com.bbi.vmBackend.da.RequestHome;
import com.bbi.vmBackend.da.SingletonDBConnection;
import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.History;
import com.bbi.vmBackend.da.dao.Notifications;
import com.bbi.vmBackend.da.dao.OS;
import com.bbi.vmBackend.da.dao.Request;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class RequestImpl extends DBConnection{
	private static Employee employee;
	private static OS os;
	static DataAccessFacade facade = new DataAccessFacade();

	public RequestImpl() {
		employee = new Employee();
		os = new OS();
	}
	
	public List<Request> getRequestByType(String type) {
		 String getOneQuery = "SELECT * FROM request WHERE type = ? and status = 0;";
		 Connection jdbcConnection = getConnection();
		 List<Request> listRequest = new ArrayList<Request>();

		 Statement statement;
		try {
			statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(getOneQuery);

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				int CPU = resultSet.getInt("CPU");
				int RAM = resultSet.getInt("RAM");
				int HD = resultSet.getInt("HD");
				Date creation_date = resultSet.getDate("creation_date");
				Date expiring_date = resultSet.getDate("expiring_date");
				Date handeled_date = resultSet.getDate("handeled_date");
				int request_user_id = resultSet.getInt("request_user_id");
				String internetFacing = resultSet.getString("internetFacing");
				Date submited_date = resultSet.getDate("submited_date");
				Date approved_date = resultSet.getDate("approved_date");
				int period = resultSet.getInt("period");
				int os_id = resultSet.getInt("os_id");
				String status = resultSet.getString("status");
				Request request = new Request(id, CPU, RAM, HD, creation_date,
						expiring_date, handeled_date, request_user_id, internetFacing,
						submited_date, approved_date, period, os_id,status);
				listRequest.add(request);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listRequest;
			
	}
	
	public void notifyUserByPeriod(Request request) {
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		Notifications notification = new Notifications();
		NotificationBL notificationBL = new NotificationBL();
		
		notification.setContent("Reminder");
		notification.setDate(date);
		notification.setFrom("System");
		notification.setNoti_RequestId(request.getId());
		notification.setNoti_UserId(employee.getUserId());
		notification.setSeen("1");
		notification.setType("Reminder");
		notification.setExpired(request.getExpiring_date().toString());
		notification.setReminder("1");

		notificationBL.insert(notification);
	}

	public static Request createTicket(Request request) {

		// OSHome osHome = new OSHome();
		// NotificationsHome notificationsHome = new NotificationsHome();
		// HistoryHome historyHome = new HistoryHome();

//		RequestHome requestHome = new RequestHome();
		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		OSImpl osimpl = new OSImpl();
		NotificationBL notificationBL = new NotificationBL();
		HistoryBL historyBL = new HistoryBL();

		// meen el user elly 3ml el request dah (request_user_id)?

		if (facade.insert(request)) { // insert complete

			os.setOsName(request.getOs_type());
			os.setOs_user_id(request.getRequest_user_id());
//			osimpl.insert(os);

			notification.setContent("You created new request");
			notification.setDate(date);
			notification.setFrom("System");
			notification.setNoti_RequestId(request.getId());
			notification.setNoti_UserId(employee.getUserId());
			notification.setSeen("0");
			notification.setType("new erquest");

			notificationBL.insert(notification);
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(request.getId());
			history.setHistory_UserId(employee.getUserId());
			history.setUserType(employee.getType());

//			historyImpl.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return request;
		} else { // insert failed
			return null;
		}
	}

	public static Request updateTicket(Request request) {

		RequestHome requestHome = new RequestHome();
//		OsImpl osimpl = new OsImpl();
		NotificationBL notificationBL = new NotificationBL();
		HistoryBL historyBL = new HistoryBL();

		// OSHome osHome = new OSHome();
		Notifications notification = new Notifications();
		// NotificationsHome notificationsHome = new NotificationsHome();
		// HistoryHome historyHome = new HistoryHome();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());

		// meen el user elly 3ml el request update dah (request_user_id)?

		if (facade.update(request)) { // update complete

			os.setOsName(request.getOs_type());
			os.setOs_user_id(request.getRequest_user_id());
//			osimpl.update(os);

			notification.setContent("You created new request");
			notification.setDate(date);
			notification.setFrom("System");
			notification.setNoti_RequestId(request.getId());
			notification.setNoti_UserId(employee.getUserId());
			notification.setSeen("1");
			notification.setType("update request");

			notificationBL.insert(notification);

			history.setDate(date);
			history.setHistory_RequestId(request.getId());
			history.setHistory_UserId(employee.getUserId());
			history.setUserType(employee.getType());

//			historyImpl.insert(history);

			// update os
			// create notification to user (depend on user type)
			// store event in history table

			return request;
		} else { // update failed
			return null;
		}
	}

	public static Request deleteTicket(Request request) {

		RequestHome requestHome = new RequestHome();
//		OsImpl osimpl = new OsImpl();
		NotificationBL notificationBL = new NotificationBL();
		HistoryBL historyBL = new HistoryBL();
		// OSHome osHome = new OSHome();
		Notifications notification = new Notifications();
		// NotificationsHome notificationsHome = new NotificationsHome();
		// HistoryHome historyHome = new HistoryHome();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());

		// meen el user elly 3ml el request delete dah (request_user_id)?

		if (facade.delete(request)) { // delete complete
			os.setOsName(request.getOs_type());
			os.setOs_user_id(request.getRequest_user_id());
//			osimpl.delete(os);

			notification.setContent("You created new request");
			notification.setDate(date);
			notification.setFrom("System");
			notification.setNoti_RequestId(request.getId());
			notification.setNoti_UserId(employee.getUserId());
			notification.setSeen("1");
			notification.setType("delete request");

			notificationBL.insert(notification);

			history.setDate(date);
			history.setHistory_RequestId(request.getId());
			history.setHistory_UserId(employee.getUserId());
			history.setUserType(employee.getType());

//			historyImpl.insert(history);
			// delete this request from os table
			// create notification to user (depend on user type)
			// store event in history table

			return request;
		} else { // delete failed
			return null;
		}
	}

	public static Request getTicket(Request request) {

		RequestHome requestHome = new RequestHome();
		NotificationBL notificationBL = new NotificationBL();
		HistoryBL historyBL = new HistoryBL();
		Notifications notification = new Notifications();
		// NotificationsHome notificationsHome = new NotificationsHome();
		// HistoryHome historyHome = new HistoryHome();
		// OSHome osHome = new OSHome();

		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());

		// meen el user elly 3ml el request select dah (request_user_id)?

		if (facade.getById(request) != null) { // select complete
			notification.setContent("You created new request");
			notification.setDate(date);
			notification.setFrom("System");
			notification.setNoti_RequestId(request.getId());
			notification.setNoti_UserId(employee.getUserId());
			notification.setSeen("1");
			notification.setType("delete request");

			notificationBL.insert(notification);

			history.setDate(date);
			history.setHistory_RequestId(request.getId());
			history.setHistory_UserId(employee.getUserId());
			history.setUserType(employee.getType());

//			historyImpl.insesrt(history);
			// select os type
			// store event in history table

			return request;
		} else { // delete failed
			return null;
		}
	}

	public List<Request> listAll() {
		List<Request> listRequest = new ArrayList<Request>();
		String selectQuery = "SELECT * FROM os, request where request_user_id = id";
		try {
			Connection jdbcConnection = getConnection();
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int CPU = resultSet.getInt("CPU");
				int RAM = resultSet.getInt("RAM");
				int HD = resultSet.getInt("HD");
				Date creation_date = resultSet.getDate("creation_date");
				Date expiring_date = resultSet.getDate("expiring_date");
				Date handeled_date = resultSet.getDate("handeled_date");
				int request_user_id = resultSet.getInt("request_user_id");
				String internetFacing = resultSet.getString("internetFacing");
				Date submited_date = resultSet.getDate("submited_date");
				Date approved_date = resultSet.getDate("approved_date");
				int period = resultSet.getInt("period");
				int os_id = resultSet.getInt("os_id");
				String status = resultSet.getString("status");
				Request request = new Request(id, CPU, RAM, HD, creation_date,
						expiring_date, handeled_date, request_user_id, internetFacing,
						submited_date, approved_date, period, os_id,status);
				listRequest.add(request);
			}

			resultSet.close();
			statement.close();
			jdbcConnection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return listRequest;
		
	}
}
