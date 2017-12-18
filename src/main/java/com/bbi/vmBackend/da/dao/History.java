package com.bbi.vmBackend.da.dao;

import java.util.Date;

public class History implements DaoObject {

	private int id;
	private String userType;
	private Date date;
	private String name;
	private int history_UserId;
	private int history_RequestId;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHistory_UserId() {
		return history_UserId;
	}

	public void setHistory_UserId(int history_UserId) {
		this.history_UserId = history_UserId;
	}

	public int getHistory_RequestId() {
		return history_RequestId;
	}

	public void setHistory_RequestId(int history_RequestId) {
		this.history_RequestId = history_RequestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
