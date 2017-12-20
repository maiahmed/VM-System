package com.bbi.vmBackend.da.dao;

import java.util.Date;

public class Request implements DaoObject {
	private int id;
	private int CPU;
	private int RAM;
	private int HD;
	private Date creation_date;
	private Date expiring_date;
	private Date handeled_date;
	private int request_user_id;
	private String internetFacing;
	private Date submited_date;
	private Date approved_date;
	private int period;
	private int os_id;
	private String os_type;
	private String status; /// 0 1 2
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}
	
	public String getOs_type() {
		return os_type;
	}
	
	public Request() {
		// TODO Auto-generated constructor stub
	}
	
	public Request(int id, int cPU, int rAM, int hD, Date creation_date,
			Date expiring_date, Date handeled_date, int request_user_id,
			String internetFacing, Date submited_date, Date approved_date,
			int period, int os_id, String status) {
		super();
		this.id = id;
		CPU = cPU;
		RAM = rAM;
		HD = hD;
		this.creation_date = creation_date;
		this.expiring_date = expiring_date;
		this.handeled_date = handeled_date;
		this.request_user_id = request_user_id;
		this.internetFacing = internetFacing;
		this.submited_date = submited_date;
		this.approved_date = approved_date;
		this.period = period;
		this.os_id = os_id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCPU() {
		return CPU;
	}

	public void setCPU(int cPU) {
		CPU = cPU;
	}

	public int getRAM() {
		return RAM;
	}

	public void setRAM(int rAM) {
		RAM = rAM;
	}

	public int getHD() {
		return HD;
	}

	public void setHD(int hD) {
		HD = hD;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getExpiring_date() {
		return expiring_date;
	}

	public void setExpiring_date(Date expiring_date) {
		this.expiring_date = expiring_date;
	}

	public Date getHandeled_date() {
		return handeled_date;
	}

	public void setHandeled_date(Date handeled_date) {
		this.handeled_date = handeled_date;
	}

	public int getRequest_user_id() {
		return request_user_id;
	}

	public void setRequest_user_id(int request_user_id) {
		this.request_user_id = request_user_id;
	}

	public String isInternetFacing() {
		return internetFacing;
	}

	public void setInternetFacing(String internetFacing) {
		this.internetFacing = internetFacing;
	}

	public Date getSubmited_date() {
		return submited_date;
	}

	public void setSubmited_date(Date submited_date) {
		this.submited_date = submited_date;
	}

	public Date getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getOs_id() {
		return os_id;
	}

	public void setOs_id(int os_id) {
		this.os_id = os_id;
	}

}
