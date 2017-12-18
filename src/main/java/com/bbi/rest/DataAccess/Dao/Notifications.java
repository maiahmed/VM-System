package com.bbi.rest.DataAccess.Dao;

import java.util.Date;;

public class Notifications implements DaoObject{

	private int id;
	private String content;
	private String from;
	private Date date;
	private int noti_RequestId;
	private int noti_UserId;
	private String type;
	private String hidden;
	private String seen;
	private String reminder;
	private String expired;
	private String extra_Notif;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNoti_RequestId() {
		return noti_RequestId;
	}

	public void setNoti_RequestId(int noti_RequestId) {
		this.noti_RequestId = noti_RequestId;
	}

	public int getNoti_UserId() {
		return noti_UserId;
	}

	public void setNoti_UserId(int noti_UserId) {
		this.noti_UserId = noti_UserId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getSeen() {
		return seen;
	}

	public void setSeen(String seen) {
		this.seen = seen;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public String getExtra_Notif() {
		return extra_Notif;
	}

	public void setExtra_Notif(String extra_Notif) {
		this.extra_Notif = extra_Notif;
	}

}
