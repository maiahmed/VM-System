package com.bbi.rest.DataAccess.Doa;

public class Employee {

	private int UserId;
	private String Name;
	private String Email;
	private String Password;
	private String Type;
	private String ExtraEmpl;
	private int Manager;

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getExtraEmpl() {
		return ExtraEmpl;
	}

	public void setExtraEmpl(String extraEmpl) {
		ExtraEmpl = extraEmpl;
	}

	public int getManager() {
		return Manager;
	}

	public void setManager(int manager) {
		Manager = manager;
	}

}
