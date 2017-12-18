package com.bbi.rest.DataAccess.Dao;

public class OS implements DaoObject {

	private int osId;
	private String osName;
	private String osExtra_OS;
	private int osManager;

	public int getOsId() {
		return osId;
	}

	public void setOsId(int osId) {
		this.osId = osId;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsExtra_OS() {
		return osExtra_OS;
	}

	public void setOsExtra_OS(String osExtra_OS) {
		this.osExtra_OS = osExtra_OS;
	}

	public int getOsManager() {
		return osManager;
	}

	public void setOsManager(int osManager) {
		this.osManager = osManager;
	}

}