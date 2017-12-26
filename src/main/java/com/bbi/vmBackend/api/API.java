package com.bbi.vmBackend.api;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bbi.vmBackend.businessLogic.*;
import com.bbi.vmBackend.da.dao.*;

@Path("/api")
public class API {
	// http://localhost:8080/VM_Syatem/rest/api/

	@POST
	@Path("/{userName}/{password}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response login(@PathParam("userName") String userName, @PathParam("password") String password) {
		System.out.println("-----------i'm in Login-------------------");
		Validation val = new Validation();
		Response response = null;
		if (val.loginValidation(userName, password)) {
			Authentication authentication = new Authentication();
			Session session = authentication.authenticate(userName, password);

			Response.ResponseBuilder rb = Response.ok("the Login response");
			response = rb.header("session_id", session.getSessionId()).header("user_id", session.getUser_Id())
					.header("token", session.getToken()).header("key", session.getKey()).build();
		} else {
			System.out.println("-------------user not valid--------------");
		}
		return response;
	}

	// notif_user_id
	@GET
	@Path("/{user_id}/{token}/{key}/{lastInsertion}/{lastUpdate}/{notif_user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DaoObject> getNotification(@PathParam("user_id") int user_id, @PathParam("token") int token,
			@PathParam("key") String key, @PathParam("lastInsertion") Date lastInsertion,
			@PathParam("lastUpdate") Date lastUpdate, @PathParam("notif_user_id") int notifUserId) {

		NotificationBL notificationBL = new NotificationBL();
		Session session = new Session();
		session.setUser_Id(user_id);
		session.setToken(token);
		session.setKey(key);
		session.setLastUpdate(lastUpdate);
		session.setLastInsertion(lastInsertion);

		if (validateSession(session)) { // if all session parameters is validated well
			getNextToken(session);
			return notificationBL.getNotificationsFromFacade(notifUserId);
		} else

			return null;
	}

	public boolean validateSession(Session session) {
		Validation validation = new Validation();
		return validation.validateSessionParametrs(session);

	}

	public int getNextToken(Session session) { // get next token
		NotificationBL notificationBL = new NotificationBL();
		return notificationBL.getNextToken(session); // if token > 0 get it else token will be=0

	}

	@GET // IT manager will get all the histories of all the employees
	@Path("/{user_id}/{token}/{key}/{lastInsertion}/{lastUpdate}/{getHistroy}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DaoObject> getHistory(@PathParam("user_id") int user_id, @PathParam("token") int token,
			@PathParam("key") String key, @PathParam("lastInsertion") Date lastInsertion,
			@PathParam("lastUpdate") Date lastUpdate) {

		HistoryBL historyBL = new HistoryBL();

		Session session = new Session();
		session.setUser_Id(user_id);
		session.setToken(token);
		session.setKey(key);
		session.setLastUpdate(lastUpdate);
		session.setLastInsertion(lastInsertion);
		if (validateSession(session)) { // validate the session input within api
			getNextToken(session); // then get the next session token
			return historyBL.listAllHisBL();
		} else
			return null;
	}
}
