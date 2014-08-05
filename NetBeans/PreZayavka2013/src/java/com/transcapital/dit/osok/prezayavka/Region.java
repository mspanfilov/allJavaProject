package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;

public class Region implements Serializable {

	private int regID;
	private String regName;
	
	public Region(int regID, String regName) {
		super();
		this.regID = regID;
		this.regName = regName;
	}
	
	public int getRegID() {
		return regID;
	}
	public void setRegID(int regID) {
		this.regID = regID;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	
}
