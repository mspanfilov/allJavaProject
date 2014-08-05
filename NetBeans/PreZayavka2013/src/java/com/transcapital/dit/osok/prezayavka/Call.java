/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.prezayavka;

import java.util.Date;

/**
 *
 * @author panfilov_ms
 */

public class Call {
	
	private int callID;
    private Date fDate;
        private String fSDate;
    private Date tDate;
        private String tSDate;
    private int dur;
    private String operator;
    private String note;
    private Date nDate;
        private String nSDate;
    private String statusID;
    private String statusName;

    public Call(int callID, Date fDate, String fSDate, Date tDate, String tSDate, int dur, String operator, String note, Date nDate, String nSDate, String statusID, String statusName) {
        this.callID = callID;
        this.fDate = fDate;
        this.fSDate = fSDate;
        this.tDate = tDate;
        this.tSDate = tSDate;
        this.dur = dur;
        this.operator = operator;
        this.note = note;
        this.nDate = nDate;
        this.nSDate = nSDate;
        this.statusID = statusID;
        this.statusName = statusName;
    }

    Call() {
        
    }
	    
	public int getCallID() {
		return callID;
	}
	public void setCallID(int callID) {
		this.callID = callID;
	}
	public Date getFDate() {
		return fDate;
	}
	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}
	public Date getTDate() {
		return tDate;
	}
	public void setTDate(Date tDate) {
		this.tDate = tDate;
	}
	public int getDur() {
		return dur;
	}
	public void setDur(int dur) {
		this.dur = dur;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getNDate() {
		return nDate;
	}
	public void setNDate(Date nDate) {
		this.nDate = nDate;
	}
	public String getStatusID() {
		return statusID;
	}
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
            public String getFSDate() {
        return fSDate;
    }

    public void setFSDate(String fSDate) {
        this.fSDate = fSDate;
    }

    public String getNSDate() {
        return nSDate;
    }

    public void setNSDate(String nSDate) {
        this.nSDate = nSDate;
    }

    public String getTSDate() {
        return tSDate;
    }

    public void setTSDate(String tSDate) {
        this.tSDate = tSDate;
    }

    // for inserting (clear on close)
    public void rewrite(Call in) {

        this.callID = in.callID;
        this.fDate = in.fDate;
        this.fSDate = in.fSDate;
        this.tDate = in.tDate;
        this.tSDate = in.tSDate;
        this.dur = in.dur;
        this.operator = in.operator;
        this.note = in.note;
        this.nDate = in.nDate;
        this.nSDate = in.nSDate;
        this.statusID = in.statusID;
        this.statusName = in.statusName;
	}
    
}
