package com.bbi.vmBackend.businessLogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.DBConnection;
import com.bbi.vmBackend.da.RequestHome;
import com.bbi.vmBackend.da.dao.History;
import com.bbi.vmBackend.da.dao.Notifications;
import com.bbi.vmBackend.da.dao.Host;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class HostImpl extends DBConnection{

	public static Host createHost(Host host) {
		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?

		if (facade.insert(host)) { // insert complete

			notification.setContent("You created new Host");
			notification.setDate(date);
//			notification.setSeen("0");
			notification.setType("new Host");

			facade.insert(notification);
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(host.getHost_id());

			facade.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return host;
		} else { // insert failed
			return null;
		}
	}

	public static Host updateTicket(Host host) {
		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?

		if (facade.update(host)) { // update complete

			
			notification.setContent("update Host");
			notification.setDate(date);
//			notification.setSeen("0");

			facade.insert(host);
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(host.getHost_id());

			facade.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return host;
		} else { // insert failed
			return null;
		}
	}

	public static Host deleteTicket(Host host) {

		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?

		if (facade.delete(host)) { // insert complete

			
			notification.setContent("Delete Host");
			notification.setDate(date);
//			notification.setSeen("0");
			notification.setType("new Host");

			facade.insert(host);
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(host.getHost_id());

			facade.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return host;
		} else { // insert failed
			return null;
		}
	}

	public static Host getTicket(Host host) {

		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?

		if (facade.getById(host) != null) { // get completes
			
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(host.getHost_id());

			facade.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return host;
		} else { // insert failed
			return null;
		}
	}

	public List<Host> listAll() {
		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?
		List<Host> lisHosts = new ArrayList<Host>();
		if (facade.listAll(4) != null) { // get completes
			
			// insert os
			// create notification depend on user type
			// store event in history table

			return lisHosts;
		} else { // insert failed
			return null;
		}
	}

}
