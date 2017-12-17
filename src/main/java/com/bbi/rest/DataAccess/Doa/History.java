package com.bbi.rest.DataAccess.Doa;

import java.sql.*;
public class History {
	
	private int Id;
	private String UserType;
	private Date Date;
	private String Name;
	private int History_UserId;
	private int History_RequestId;
	private String Status;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getHistory_UserId() {
		return History_UserId;
	}

	public void setHistory_UserId(int history_UserId) {
		History_UserId = history_UserId;
	}

	public int getHistory_RequestId() {
		return History_RequestId;
	}

	public void setHistory_RequestId(int history_RequestId) {
		History_RequestId = history_RequestId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

}
