package com.eg.pack;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Country implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int id;
private String countryName;
private long countryCode;


public Country(){}

public Country(int id,String countryName,long countryCode)
{
	this.id = id;
	this.countryName = countryName;
	this.countryCode = countryCode;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCountryName() {
	return countryName;
}
public void setCountryName(String countryName) {
	this.countryName = countryName;
}
public long getCountryCode() {
	return countryCode;
}
public void setCountryCode(long countryCode) {
	this.countryCode = countryCode;
}


}
