package com.bbi.vmBackend.businessLogic;

import java.util.List;

import com.bbi.vmBackend.da.RequestHome;
import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.OS;
import com.bbi.vmBackend.da.dao.Request;

public class RequestImpl {
	Employee employee;
	OS os;
	public void notifyUserByPeriod() {

	}

	public static Request createTicket(Request request) {

		RequestHome r = new RequestHome();

		// meen el user elly 3ml el request dah (request_user_id)?

		if (r.insert(request)) { // insert complete

			// insert os
			// create notification depend on user type
			// store event in history table

			return request;
		} else { // insert failed
			return null;
		}
	}

	public static Request updateTicket(Request request) {

		RequestHome r = new RequestHome();

		// meen el user elly 3ml el request update dah (request_user_id)?

		if (r.update(request)) { // update complete

			// update os
			// create notification to user (depend on user type)
			// store event in history table

			return request;
		} else { // update failed
			return null;
		}
	}

	public static Request deleteTicket(Request request) {

		RequestHome r = new RequestHome();

		// meen el user elly 3ml el request delete dah (request_user_id)?

		if (r.delete(request)) { // delete complete

			// delete this request from os table
			// create notification to user (depend on user type)
			// store event in history table

			return request;
		} else { // delete failed
			return null;
		}
	}

	public static Request getTicket(Request request) {

		RequestHome r = new RequestHome();

		// meen el user elly 3ml el request select dah (request_user_id)?

		if (r.getById(request) != null) { // select complete

			// select os type
			// store event in history table

			return request;
		} else { // delete failed
			return null;
		}
	}

}
