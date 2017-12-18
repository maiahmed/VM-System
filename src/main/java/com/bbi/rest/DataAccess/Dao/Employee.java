package com.bbi.rest.DataAccess.Dao;

public class Employee implements DaoObject {

	private int userId;
	private String name;
	private String email;
	private String password;
	private String type;
	private String extraEmpl;
	private int manager;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtraEmpl() {
		return extraEmpl;
	}

	public void setExtraEmpl(String extraEmpl) {
		this.extraEmpl = extraEmpl;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

}