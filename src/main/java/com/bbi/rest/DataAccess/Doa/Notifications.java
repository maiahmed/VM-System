package com.bbi.rest.DataAccess.Doa;

import java.sql.*;

public class Notifications {
	
	private int id;
	private String Content;
	private String From;
	private Date Date;
	private int Noti_RequestId;
	private int Noti_UserId;
	private String Type;
	private String Hidden;
	private String Seen;
	private String Reminder;
	private String Expired;
	private String Extra_Notif;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String from) {
		From = from;
	}



	public int getNoti_RequestId() {
		return Noti_RequestId;
	}

	public void setNoti_RequestId(int noti_RequestId) {
		Noti_RequestId = noti_RequestId;
	}

	
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getHidden() {
		return Hidden;
	}

	public void setHidden(String hidden) {
		Hidden = hidden;
	}

	public String getSeen() {
		return Seen;
	}

	public void setSeen(String seen) {
		Seen = seen;
	}

	public String getReminder() {
		return Reminder;
	}

	public void setReminder(String reminder) {
		Reminder = reminder;
	}

	public String getExpired() {
		return Expired;
	}

	public void setExpired(String expired) {
		Expired = expired;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getExtra_Notif() {
		return Extra_Notif;
	}

	public void setExtra_Notif(String extra_Notif) {
		Extra_Notif = extra_Notif;
	}

	public int getNoti_UserId() {
		return Noti_UserId;
	}

	public void setNoti_UserId(int noti_UserId) {
		Noti_UserId = noti_UserId;
	}

}
