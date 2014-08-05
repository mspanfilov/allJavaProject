/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author panfilov_ms
 */
public class ReqStatuses implements Serializable {
    
        private int    recID;
        private int    offID;
        private String office;
        private int    callID;
        private Date   fDate;
        private String fSDate;
        private String operator;
        private Date   nDate;
        private String nSDate;
        private int    perID;
	private String period;
        private String note;
    	private String statusID;
	private String reqStatus;
        private String curStatus;
        private String finStatus;
        private String siebel;
        private boolean bSiebel;
        private Date   mDate;
        private String mSDate;

    public ReqStatuses(int recID, int offID, String office, int callID, Date fDate, String fSDate, String operator, Date nDate, String nSDate, 
                       int perID, String period, String note, String statusID, String reqStatus, String curStatus, String finStatus, String siebel, boolean bSiebel, Date mDate, String mSDate) {
        this.recID = recID;
        this.offID = offID;
        this.office = office;
        this.callID = callID;
        this.fDate = fDate;
        this.fSDate = fSDate;
        this.operator = operator;
        this.nDate = nDate;
        this.nSDate = nSDate;
        this.note = note;
        this.perID = perID;
        this.period = period;
        this.statusID = statusID;
        this.reqStatus = reqStatus;
        this.curStatus = curStatus;
        this.finStatus = finStatus;
        this.siebel = siebel;
        this.bSiebel = bSiebel;
        this.mDate = mDate;
        this.mSDate = mSDate;
    }

    public int getCallID() {
        return callID;
    }

    public Date getFDate() {
        return fDate;
    }

    public String getFSDate() {
        return fSDate;
    }

    public Date getNDate() {
        return nDate;
    }

    public String getNSDate() {
        return nSDate;
    }

    public String getNote() {
        return note;
    }

    public int getOffID() {
        return offID;
    }

    public String getOffice() {
        return office;
    }

    public String getOperator() {
        return operator;
    }

    public int getPerID() {
        return perID;
    }

    public String getPeriod() {
        return period;
    }

    public int getRecID() {
        return recID;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setCallID(int callID) {
        this.callID = callID;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public void setfSDate(String fSDate) {
        this.fSDate = fSDate;
    }

    public void setnDate(Date nDate) {
        this.nDate = nDate;
    }

    public void setnSDate(String nSDate) {
        this.nSDate = nSDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setOffID(int offID) {
        this.offID = offID;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setPerID(int perID) {
        this.perID = perID;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setRecID(int recID) {
        this.recID = recID;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    public String getFinStatus() {
        return finStatus;
    }

    public void setFinStatus(String finStatus) {
        this.finStatus = finStatus;
    }

    public Date getMDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getSiebel() {
        return siebel;
    }

    public void setSiebel(String siebel) {
        this.siebel = siebel;
    }

    public boolean getBSiebel() {
        return bSiebel;
    }

    public void setbSiebel(boolean bSiebel) {
        this.bSiebel = bSiebel;
    }
    
    

    public String getMSDate() {
        return mSDate;
    }

    public void setmSDate(String mSDate) {
        this.mSDate = mSDate;
    }
    
}
