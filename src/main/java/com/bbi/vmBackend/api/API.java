package com.bbi.vmBackend.api;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bbi.vmBackend.api.PATCH;
import com.bbi.vmBackend.api.Validation;
import com.bbi.vmBackend.businessLogic.*;
import com.bbi.vmBackend.da.dao.*;

import org.json.simple.JSONObject;

import com.bbi.vmBackend.businessLogic.Authentication;
import com.bbi.vmBackend.businessLogic.CommentBussLogic;
import com.bbi.vmBackend.da.dao.Comment;
import com.bbi.vmBackend.da.dao.Session;

@Path("/api")
public class API {
	// http://localhost:8080/VM_Syatem/rest/api/

	@POST
@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(JSONObject inputJsonObj) {
		System.out.println("-----------i'm in Login-------------------");
		Validation val = new Validation();
		Response response = null;
		String userName = (String) inputJsonObj.get("userName");
		String password = (String) inputJsonObj.get("password");
		System.out.println("----> " + userName + " " + password);
		if (val.loginValidation(userName, password)) {
			Authentication authentication = new Authentication();
			Session session = authentication.authenticate(userName, password);

			if (session != null) {
				Response.ResponseBuilder rb = Response.ok("the Login response");
				response = rb.header("session_id", session.getSessionId())
						.header("user_id", session.getUser_Id())
						.header("token", session.getToken())
						.header("key", session.getKey()).build();
			}
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

//	@GET // IT manager will get all the histories of all the employees
//	@Path("/{user_id}/{token}/{key}/{lastInsertion}/{lastUpdate}/{getHistroy}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<DaoObject> getHistory(@PathParam("user_id") int user_id, @PathParam("token") int token,
//			@PathParam("key") String key, @PathParam("lastInsertion") Date lastInsertion,
//			@PathParam("lastUpdate") Date lastUpdate) {
//
//		HistoryBL historyBL = new HistoryBL();
//
//		Session session = new Session();
//		session.setUser_Id(user_id);
//		session.setToken(token);
//		session.setKey(key);
//		session.setLastUpdate(lastUpdate);
//		session.setLastInsertion(lastInsertion);
//		if (validateSession(session)) { // validate the session input within api
//			getNextToken(session); // then get the next session token
//			return historyBL.listAllHisBL();
//		} else
//			return null;
//	
//		Authentication authentication = new Authentication();
//		authentication.validateSession(session); // from mai
//		return session;
//	}

	@POST
	@Path("/{user_id}/{token}/{key}/{session_id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getComment(@PathParam("user_id") int user_id,
			@PathParam("token") int token, @PathParam("key") String key,
			@PathParam("sessionId") int sessionId) {
		System.out.println("annnnnnnnnnnnnnnnn commmmmmmment");
		Authentication authentication = new Authentication();
		Session session = new Session();
		session.setUser_Id(user_id);
		session.setKey(key);
		session.setSessionId(sessionId);
		Comment comment = null;
		int nextToken = authentication.validateSession(session);
		System.out.println("nex " + nextToken);
		if (nextToken != -1) {
			CommentBussLogic bussLogic = new CommentBussLogic();
			System.out.println(session.getUser_Id());
			comment = bussLogic.getComment(user_id);
			if (comment != null)
				System.out.println("Comment Done");
			System.out.println(comment.getComment_id());
		} else {
			System.out.println("Operation Failed!!");
			session = null;
		}
		return comment;
	}

	@DELETE
	@Path("/{user_id}/{token}/{key}/{session_id}/{comment_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteComment(@PathParam("user_id") int user_id,
			@PathParam("token") int token, @PathParam("key") String key,
			@PathParam("sessionId") int sessionId,
			@PathParam("comment_id") int comment_id) {
		System.out.println("annnnnnnnnnnnnnnnn commmmmmmment del ");
		Authentication authentication = new Authentication();
		Comment comment = new Comment();
		boolean isDeleted = false;
		comment.setComment_id(comment_id);

		Session session = new Session();
		session.setUser_Id(user_id);
		session.setKey(key);
		session.setSessionId(sessionId);
		int nextToken = authentication.validateSession(session);
		System.out.println("nex " + nextToken);
		if (nextToken != -1) {
			CommentBussLogic bussLogic = new CommentBussLogic();
			System.out.println(session.getUser_Id());
			isDeleted = bussLogic.deleteComment(comment);
			if (isDeleted)
				System.out.println("Comment Done");
			System.out.println(comment.getComment_id());
		} else {

			System.out.println("Operation Failed!!");
			session = null;
		}
		return isDeleted;
	}

	@PATCH
	@Path("/{user_id}/{token}/{key}/{session_id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateComment(@PathParam("user_id") int user_id,
			@PathParam("token") int token, @PathParam("key") String key,
			@PathParam("sessionId") int sessionId, JSONObject commentJsonObject) {
		System.out.println("annnnnnnnnnnnnnnnn commmmmmmment update "
				+ commentJsonObject + " "
				+ (String) commentJsonObject.get("content"));
		Authentication authentication = new Authentication();
		Comment comment = new Comment();
		boolean isUpdated = false;
		comment.setContent((String) commentJsonObject.get("content"));
		comment.setComment_id((int) commentJsonObject.get("comment_id"));

		Session session = new Session();
		session.setUser_Id(user_id);
		session.setKey(key);
		session.setSessionId(sessionId);
		System.out.println("sss "+ session.getUser_Id()+" "+session.getSessionId());
		int nextToken = authentication.validateSession(session);
		System.out.println("nex " + nextToken);
		if (nextToken != -1) {
			CommentBussLogic bussLogic = new CommentBussLogic();
			System.out.println(session.getUser_Id());
			isUpdated = bussLogic.updateComment(comment);
			if (isUpdated)
				System.out.println("Comment Done");
			System.out.println(comment.getComment_id());
		} else {

			System.out.println("Operation Failed!!");
			session = null;
		}
		return isUpdated;
	}

	

	@POST
	@Path("/{user_id}/{token}/{key}/{session_id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean insertComment(@PathParam("user_id") int user_id,
			@PathParam("token") int token, @PathParam("key") String key,
			@PathParam("sessionId") int sessionId, JSONObject commentJsonObject) {
		System.out.println("annnnnnnnnnnnnnnnn commmmmmmment insert "
				+ commentJsonObject + " "
				+ (String) commentJsonObject.get("content"));
		Authentication authentication = new Authentication();
		Comment comment = new Comment();
		boolean isInserted = false;
		comment.setContent((String) commentJsonObject.get("content"));
		comment.setContent((String) commentJsonObject.get("comment_request_id"));
		comment.setComment_user_id(user_id);
		Session session = new Session();
		session.setUser_Id(user_id);
		session.setKey(key);
		session.setSessionId(sessionId);
		System.out.println("sss "+ session.getUser_Id()+" "+session.getSessionId());
		int nextToken = authentication.validateSession(session);
		System.out.println("nex " + nextToken);
		if (nextToken != -1) {
			CommentBussLogic bussLogic = new CommentBussLogic();
			System.out.println(session.getUser_Id());
			isInserted = bussLogic.insertComment(comment);
			if (isInserted)
				System.out.println("Comment Done");
			System.out.println(comment.getComment_id());
		} else {

			System.out.println("Operation Failed!!");
			session = null;
		}
		return isInserted;
	}

}
