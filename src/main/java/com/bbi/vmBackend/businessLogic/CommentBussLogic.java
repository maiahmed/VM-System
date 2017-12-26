package com.bbi.vmBackend.businessLogic;

import com.bbi.vmBackend.da.dao.Comment;
import com.bbi.vmBackend.da.dao.History;
import com.bbi.vmBackend.da.dao.Notifications;
import com.bbi.vmBackend.facade.DataAccessFacade;

public class CommentBussLogic {
	public static Comment getComment(int user_id) {

		Notifications notification = new Notifications();
		History history = new History();
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?
		Comment comment = new Comment();
		comment.setComment_user_id(user_id);
		System.out.println("comment----))) > " + comment.getComment_user_id());
		comment = (Comment) facade.getById(comment);
		if (comment != null) { // get completes

			// notificationsHome.insert(notification);
			// history
			System.out.println("ana la2eet el comment");
			return comment;
		} else { // insert failed
			return null;
		}
	}

	public  boolean deleteComment(Comment comment) {

		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?
		System.out.println("comment----))) > " + comment.getComment_user_id());
		boolean check = facade.delete(comment);
		if (check) { // delete completes

			// notificationsHome.insert(notification);
			// history
			System.out.println("ana la2eet el comment w ms7too");
			return true;
		} else { // insert failed
			return false;
		}
	}
	
	public  boolean updateComment(Comment comment) {

		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?
		System.out.println("comment----))) > " + comment.getComment_user_id());
		boolean check = facade.update(comment);
		if (check) { // update completes

			// notificationsHome.insert(notification);
			// history
			System.out.println("ana la2eet el comment w update-o");
			return true;
		} else { // insert failed
			return false;
		}
	}

	
	public  boolean insertComment(Comment comment) {

		DataAccessFacade facade = new DataAccessFacade();
		// meen el user elly 3ml el request dah (request_user_id)?
		System.out.println("comment----))) > " + comment.getComment_user_id());
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		comment.setDate(date);
		boolean check = facade.insert(comment);
		if (check) { // update completes

			// notificationsHome.insert(notification);
			// history
			System.out.println("ana la2eet el comment w update-o");
			return true;
		} else { // insert failed
			return false;
		}
	}
}
