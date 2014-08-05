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
public class ReqStatus implements Serializable {
    
    	private String reqStatusID;
	private String reqStatusName;
	
	public ReqStatus(String reqStatusID, String reqStatusName) {
		super();
		this.reqStatusID = reqStatusID;
		this.reqStatusName = reqStatusName;
	}
	
	public String getReqStatusID() {
		return reqStatusID;
	}
	public void setReqStatusID(String reqStatusID) {
		this.reqStatusID = reqStatusID;
	}
	public String getReqStatusName() {
		return reqStatusName;
	}
	public void setReqStatusName(String reqStatusName) {
		this.reqStatusName = reqStatusName;
	}
    
}
