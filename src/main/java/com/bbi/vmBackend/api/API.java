package com.bbi.vmBackend.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bbi.vmBackend.businessLogic.RequestImpl;
import com.bbi.vmBackend.da.RequestHome;
import com.bbi.vmBackend.da.dao.Request;

@Path("/api")
public class API {
	    // http://localhost:8080/VM_Syatem/rest/api/
		@POST
		@Path("/")
		@Produces(MediaType.APPLICATION_XML)
	public Request createTicket(Request request) {
			 return RequestImpl.createTicket(request);

	}
}

