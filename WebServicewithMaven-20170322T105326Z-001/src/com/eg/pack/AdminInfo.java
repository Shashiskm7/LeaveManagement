package com.eg.pack;

public class AdminInfo {

	private String deviceid;
	
	public AdminInfo(){}
	public AdminInfo(String adminD,String aid,String deviceid)
	{
	this.deviceid = deviceid;
	}


	public String getDeviceid() {
		return deviceid;
	}


	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	
	
}
