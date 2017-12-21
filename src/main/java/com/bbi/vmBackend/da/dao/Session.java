package com.bbi.vmBackend.da.dao;

import java.util.Date;

public class Session implements DaoObject {

	private int sessionId; // auto increment
	private int user_Id; // foreign key
	private int token; // unique
	private String key;
	private Date lastInsertion; // system date
	private Date lastUpdate;

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) { //
		this.token = getUser_Id();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		
		this.key = key;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getLastInsertion() {
		return lastInsertion;
	}

	public void setLastInsertion(Date lastInsertion) {
		this.lastInsertion = lastInsertion;
	}

}
