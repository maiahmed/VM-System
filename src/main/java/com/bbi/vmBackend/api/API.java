package com.bbi.vmBackend.api;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bbi.vmBackend.businessLogic.Authentication;
import com.bbi.vmBackend.da.dao.Session;

@Path("/api")
public class API {
	// http://localhost:8080/VM_Syatem/rest/api/

	@GET
	@Path("/{userName}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public void login(@PathParam("userName") String userName, @PathParam("password") String password) {
		System.out.println("-----------i'm in Login-------------------");
		Validation val = new Validation();
		if (val.loginValidation(userName, password)) {
			Authentication authentication = new Authentication();
			authentication.isAuthenticated(userName, password);

		} else
			System.out.println("-------------user not valid--------------");
	}

	@GET
	@Path("/{user_id}/{token}/{key}/{lastInsertion}/{lastUpdate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Session getNotification(@PathParam("user_id") int user_id, @PathParam("token") int token,
			@PathParam("key") String key, @PathParam("lastInsertion") Date lastInsertion,
			@PathParam("lastUpdate") Date lastUpdate) {

		Session session = new Session();
		session.setUser_Id(user_id);
		session.setKey(key);
		session.setLastUpdate(lastUpdate);
		session.setLastInsertion(lastInsertion);
		// validateSession(session); from mai
		return session;
	}

}
