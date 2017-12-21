package com.bbi.vmBackend.businessLogic;

import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.History;
import com.bbi.vmBackend.da.dao.Comment;
import com.bbi.vmBackend.da.dao.Notifications;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class CommentImpl {
	public static Comment createComment(Comment comment) {
		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el comment dah ?

		if (facade.insert(comment)) { // insert complete

			notification.setContent("You created new Comment");
			notification.setDate(date);
//			notification.setSeen("0");
			notification.setType("new Comment");

			facade.insert(notification);
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(comment.getComment_id());

			facade.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return comment;
		} else { // insert failed
			return null;
		}
	}

	public static Comment updateTicket(Comment comment) {
		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?

		if (facade.update(comment)) { // update complete

			
			notification.setContent("update Comment");
			notification.setDate(date);
//			notification.setSeen("0");

			facade.insert(comment);
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(comment.getComment_id());

			facade.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return comment;
		} else { // insert failed
			return null;
		}
	}

	public static Comment deleteTicket(Comment comment) {

		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?

		if (facade.delete(comment)) { // insert complete

			
			notification.setContent("Delete Comment");
			notification.setDate(date);
//			notification.setSeen("0");
			notification.setType("new Comment");

			facade.insert(comment);
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(comment.getComment_id());

			facade.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return comment;
		} else { // insert failed
			return null;
		}
	}

	public static Comment getTicket(Comment comment) {

		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?

		if (facade.getById(comment) != null) { // get completes
			
			// notificationsHome.insert(notification);
			history.setDate(date);
			history.setHistory_RequestId(comment.getComment_id());

			facade.insert(history);

			// insert os
			// create notification depend on user type
			// store event in history table

			return comment;
		} else { // insert failed
			return null;
		}
	}

	public List<Comment> listAll() {
		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?
		List<Comment> lisComments = new ArrayList<Comment>();
		if (facade.listAll(4) != null) { // get completes
			
			// insert os
			// create notification depend on user type
			// store event in history table

			return lisComments;
		} else { // insert failed
			return null;
		}
	}

}
