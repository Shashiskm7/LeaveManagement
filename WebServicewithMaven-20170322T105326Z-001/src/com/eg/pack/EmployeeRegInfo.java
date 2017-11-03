package com.eg.pack;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeRegInfo implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int eid;
private String ename;
private String email;
private long pno;
private String macid;
private String deviceid;




public void setEid(int eid)
{
	this.eid = eid;
}
public int getEid()
{
	return eid;
}
public void setEname(String ename)
{
	this.ename = ename;
}
public String getEname()
{
	return ename;
}
public void setEmail(String email)
{
	this.email = email;
}
public String getEmail()
{
	return email;
}
public void setPno(long pno)
{
	this.pno = pno;
}
public long getPno()
{
	return pno;
}
public void setMacID(String macid)
{
	this.macid = macid;
}
public String getMacID()
{
	return macid;
}

public void setDeviceID(String deviceid)
{
	this.deviceid = deviceid;
}
public String getDeviceID()
{
	return deviceid;
}
}
