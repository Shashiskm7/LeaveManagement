package com.eg.pack;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LeaveApplication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String S_id;
	private String S_name;
	private String Leave_type;
	private String From_date;
	private String To_date;
	private String Status;
	
	public String getId() {
		return id;
	}
	public void setId(String i) {
		this.id = i;
	}
	public String getS_id() {
		return S_id;
	}
	public void setS_id(String s_id) {
		S_id = s_id;
	}
	public String getS_name() {
		return S_name;
	}
	public void setS_name(String s_name) {
		S_name = s_name;
	}
	public String getLeave_type() {
		return Leave_type;
	}
	public void setLeave_type(String leave_type) {
		Leave_type = leave_type;
	}
	public String getFrom_date() {
		return From_date;
	}
	public void setFrom_date(String from_date) {
		From_date = from_date;
	}
	public String getTo_date() {
		return To_date;
	}
	public void setTo_date(String to_date) {
		To_date = to_date;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	

}
