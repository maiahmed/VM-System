package com.bbi.vmBackend.api;

import java.util.Date;

import javax.ws.rs.GET;
<<<<<<< HEAD
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
=======
>>>>>>> 8714dcd02e18f9f20df84fc5ce879068f4d4c96b
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bbi.vmBackend.businessLogic.Authentication;
import com.bbi.vmBackend.da.dao.Session;

@Path("/api")
public class API {
	// http://localhost:8080/VM_Syatem/rest/api/

<<<<<<< HEAD
	@POST
=======
	@GET
>>>>>>> 8714dcd02e18f9f20df84fc5ce879068f4d4c96b
	@Path("/{userName}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
	public void login(@PathParam("userName") String userName, @PathParam("password") String password) {
=======
	public Response login(@PathParam("userName") String userName,
			@PathParam("password") String password) {
>>>>>>> 9b3ddfcd03dec709d40bfd520fe166720c5888df
		System.out.println("-----------i'm in Login-------------------");
		Validation val = new Validation();
<<<<<<< HEAD
		Response response = null;
		if (val.loginValidation(userName, password)) {
			Authentication authentication = new Authentication();
			Session session = authentication
					.authenticate(userName, password);

			Response.ResponseBuilder rb = Response.ok("the Login response");
			response = rb.header("session_id", session.getSessionId())
					.header("user_id", session.getUser_Id())
					.header("token", session.getToken())
					.header("key", session.getKey()).build();
		} else {
			System.out.println("-------------user not valid--------------");
		}
		return response;
=======
		if (val.loginValidation(userName, password)) {
			Authentication authentication = new Authentication();
<<<<<<< HEAD
			authentication.isAuthenticated(userName, password);
=======
			authentication.isUserAuthenticated(userName,password);
			
			 Response.ResponseBuilder rb = Response.ok("the test response");
		      Response response = rb.header("header1", "value1")
		                            .header("header2", "value2")
		                            .build();
			
		}
		else System.out.println("-------------user not valid--------------");
		return null;
>>>>>>> 9b3ddfcd03dec709d40bfd520fe166720c5888df
>>>>>>> 8714dcd02e18f9f20df84fc5ce879068f4d4c96b

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
