package com.bbi.vmBackend.api;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bbi.vmBackend.businessLogic.Authentication;


@Path("/api")
public class API {
	// http://localhost:8080/VM_Syatem/rest/api/


	@GET
	@Path("/{userName}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("userName") String userName,
			@PathParam("password") String password) {
		System.out.println("-----------i'm in Login-------------------");
		Validation val = new Validation();
		if(val.loginValidation(userName,password)){
			Authentication authentication = new Authentication();
			authentication.isUserAuthenticated(userName,password);
			
			 Response.ResponseBuilder rb = Response.ok("the test response");
		      Response response = rb.header("header1", "value1")
		                            .header("header2", "value2")
		                            .build();
			
		}
		else System.out.println("-------------user not valid--------------");
		return null;

	}
}
