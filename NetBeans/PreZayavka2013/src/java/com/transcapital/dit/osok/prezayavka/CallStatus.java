/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;

/**
 *
 * @author panfilov_ms
 */
public class CallStatus implements Serializable {
    
    	private String callStatusID;
	private String callStatusName;
	
	public CallStatus(String callStatusID, String callStatusName) {
		super();
		this.callStatusID = callStatusID;
		this.callStatusName = callStatusName;
	}
	
	public String getCallStatusID() {
		return callStatusID;
	}
	public void setCallReqStatusID(String callStatusID) {
		this.callStatusID = callStatusID;
	}
	public String getCallStatusName() {
		return callStatusName;
	}
	public void setCallStatusName(String callStatusName) {
		this.callStatusName = callStatusName;
	}
    
}
