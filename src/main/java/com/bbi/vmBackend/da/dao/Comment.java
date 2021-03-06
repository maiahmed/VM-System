package com.bbi.vmBackend.da.dao;

import java.util.Date;


public class Comment implements DaoObject {
	
	private int comment_id;
	private String content;
	private Date date;
	private int comment_user_id;
	private int comment_request_id;
	private String extra_comment;

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getComment_user_id() {
		return comment_user_id;
	}

	public void setComment_user_id(int comment_user_id) {
		this.comment_user_id = comment_user_id;
	}

	public int getComment_request_id() {
		return comment_request_id;
	}

	public void setComment_request_id(int comment_request_id) {
		this.comment_request_id = comment_request_id;
	}

	public String getExtra_comment() {
		return extra_comment;
	}

	public void setExtra_comment(String extra_comment) {
		this.extra_comment = extra_comment;
	}

	public Comment(int comment_id, String content, Date date,
			int comment_user_id, int comment_request_id) {
		super();
		this.comment_id = comment_id;
		this.content = content;
		this.date = date;
		this.comment_user_id = comment_user_id;
		this.comment_request_id = comment_request_id;
	}

	public Comment() {
		// TODO Auto-generated constructor stub
	}

}
