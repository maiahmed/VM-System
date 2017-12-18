package com.bbi.vmBackend.da.dao;

public class Host implements DaoObject{
	private int host_id;
	private String name;
	private String extra_host;
	private String ip;

	public int getHost_id() {
		return host_id;
	}

	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtra_host() {
		return extra_host;
	}

	public void setExtra_host(String extra_host) {
		this.extra_host = extra_host;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Host(int host_id, String name, String ip) {
		super();
		this.host_id = host_id;
		this.name = name;
		this.ip = ip;
	}

}
