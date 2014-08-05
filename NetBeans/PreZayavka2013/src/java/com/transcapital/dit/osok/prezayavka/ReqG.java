/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author panfilov_ms
 */
public class ReqG extends Req implements Serializable {
	
	private int reqID;
	private int resID;
	private Timestamp CDate;
        private String CSDate;
	private String LName;
	private String FName;
	private String SName;
	private String phone;
	private String emaill;
	private String region;
        private String source;
        private String note;
    private int regID;
    private int proID;
    private int srcID;
    private String statusID;
    private String isNew;
    private Date NDate;
    private String NSDate;
    private String perID;
    private String trfID;
    private String curID;
    private float val;
    private int term;
    private int offID;
    private String frmName;
    private int typeID;
    
    private String period;
    
    private String product;
    
    private Date MDate;
    private String MSDate;
    
    private String hphone;
    private String wphone;
    private Date BDate;
    private boolean isRes;
    private String wregion;
    private int wregID;
    private String confirm;
    private String TSeniority;
    private String CSeniority;
    

    public ReqG(int reqID, int resID, Timestamp CDate, String CSDate, String LName, String FName, String SName, String phone, String emaill, String region, String source, String note, int regID, int proID, int srcID, String statusID, String isNew, Date NDate, String NSDate, String perID, String trfID, String curID, float val, int term, int offID, String frmName, int typeID, String period, String product, Date MDate, String MSDate,
                String hphone, String wphone, Date BDate, boolean isRes, String wregion, int wregID, String confirm, String TSeniority, String CSeniority) {
        super();
        this.reqID = reqID;
        this.resID = resID;
        this.CDate = CDate;
        this.CSDate = CSDate;
        this.LName = LName;
        this.FName = FName;
        this.SName = SName;
        this.phone = phone;
        this.emaill = emaill;
        this.region = region;
        this.source = source;
        this.note = note;
        this.regID = regID;
        this.proID = proID;
        this.srcID = srcID;
        this.statusID = statusID;
        this.isNew = isNew;
        this.NDate = NDate;
        this.NSDate = NSDate;
        this.perID = perID;
        this.trfID = trfID;
        this.curID = curID;
        this.val = val;
        this.term = term;
        this.offID = offID;
        this.frmName = frmName;
        this.typeID = typeID;
        
        this.period = period;
        
        this.product = product;
        this.MDate = MDate;
        this.MSDate = MSDate;
        this.hphone = hphone;
        this.wphone = wphone;
        this.BDate = BDate;
        this.isRes = isRes;
        this.wregion = wregion;
        this.wregID = wregID;
        this.confirm = confirm;
        this.TSeniority = TSeniority;
        this.CSeniority = CSeniority;
    }
    
        ReqG() {
  
    }

    @Override
    public Timestamp getCDate() {
        return CDate;
    }
    @Override
    public void setCDate(Timestamp CDate) {
        this.CDate = CDate;
    }
    @Override
    public String getCSDate() {
        return CSDate;
    }
    @Override
    public void setCSDate(String CSDate) {
        this.CSDate = CSDate;
    }

    public String getCurID() {
        return curID;
    }

    public void setCurID(String curID) {
        this.curID = curID;
    }
    @Override
    public String getEmaill() {
        return emaill;
    }
    @Override
    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }
    @Override
    public String getFName() {
        return FName;
    }
    @Override
    public void setFName(String FName) {
        this.FName = FName;
    }
    @Override
    public String getFrmName() {
        return frmName;
    }
    @Override
    public void setFrmName(String frmName) {
        this.frmName = frmName;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }
    @Override
    public String getLName() {
        return LName;
    }
    @Override
    public void setLName(String LName) {
        this.LName = LName;
    }
    @Override
    public Date getNDate() {
        return NDate;
    }
    @Override
    public void setNDate(Date NDate) {
        this.NDate = NDate;
    }
    @Override
    public String getNSDate() {
        return NSDate;
    }
    @Override
    public void setNSDate(String NSDate) {
        this.NSDate = NSDate;
    }
    @Override
    public String getNote() {
        return note;
    }
    @Override
    public void setNote(String note) {
        this.note = note;
    }

    public int getOffID() {
        return offID;
    }

    public void setOffID(int offID) {
        this.offID = offID;
    }

    public String getPerID() {
        return perID;
    }

    public void setPerID(String perID) {
        this.perID = perID;
    }
    @Override
    public String getPhone() {
        return phone;
    }
    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public int getProID() {
        return proID;
    }
    @Override
    public void setProID(int proID) {
        this.proID = proID;
    }
    @Override
    public int getRegID() {
        return regID;
    }
    @Override
    public void setRegID(int regID) {
        this.regID = regID;
    }
    @Override
    public String getRegion() {
        return region;
    }
    @Override
    public void setRegion(String region) {
        this.region = region;
    }
    @Override
    public int getReqID() {
        return reqID;
    }
    @Override
    public void setReqID(int reqID) {
        this.reqID = reqID;
    }
    @Override
    public int getResID() {
        return resID;
    }
    @Override
    public void setResID(int resID) {
        this.resID = resID;
    }
    @Override
    public String getSName() {
        return SName;
    }
    @Override
    public void setSName(String SName) {
        this.SName = SName;
    }
    @Override
    public String getSource() {
        return source;
    }
    @Override
    public void setSource(String source) {
        this.source = source;
    }
    @Override
    public int getSrcID() {
        return srcID;
    }
    @Override
    public void setSrcID(int srcID) {
        this.srcID = srcID;
    }
    @Override
    public String getStatusID() {
        return statusID;
    }
    @Override
    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getTrfID() {
        return trfID;
    }

    public void setTrfID(String trfID) {
        this.trfID = trfID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }
    @Override    
    public String getPeriod() {
        return period;
    }
    @Override
    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String getProduct() {
        return product;
    }

    @Override
    public void setProduct(String product) {
        this.product = product;
    }
    
    @Override
    public Date getMDate() {
        return MDate;
    }

    @Override
    public void setMDate(Date MDate) {
        this.MDate = MDate;
    }

    @Override
    public String getMSDate() {
        return MSDate;
    }

    @Override
    public void setMSDate(String MSDate) {
        this.MSDate = MSDate;
    }

    public String getHphone() {
        return hphone;
    }

    public void setHphone(String hphone) {
        this.hphone = hphone;
    }

    public String getWphone() {
        return wphone;
    }

    public void setWphone(String wphone) {
        this.wphone = wphone;
    }

    public Date getBDate() {
        return BDate;
    }

    public void setBDate(Date BDate) {
        this.BDate = BDate;
    }

    public boolean isIsRes() {
        return isRes;
    }

    public void setIsRes(boolean isRes) {
        this.isRes = isRes;
    }

    public int getWregID() {
        return wregID;
    }

    public void setWregID(int wregID) {
        this.wregID = wregID;
    }

    public String getWregion() {
        return wregion;
    }

    public void setWregion(String wregion) {
        this.wregion = wregion;
    }

    public String getCSeniority() {
        return CSeniority;
    }

    public void setCSeniority(String CSeniority) {
        this.CSeniority = CSeniority;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getTSeniority() {
        return TSeniority;
    }

    public void setTSeniority(String TSeniority) {
        this.TSeniority = TSeniority;
    }
    
    
    
    
    public void convert(Req in) {        
        this.setReqID(in.getReqID());
        this.setResID(in.getResID());
        this.setFrmName(in.getFrmName());
        this.setCDate(in.getCDate());
        this.setCSDate(in.getCSDate());
        this.setLName(in.getLName());
        this.setFName(in.getFName());
        this.setSName(in.getSName());
        this.setPhone(in.getPhone());
        this.setEmaill(in.getEmaill());
        this.setRegID(in.getRegID());
        this.setRegion(in.getRegion());
        this.setProID(in.getProID());
        this.setProduct(in.getProduct());
        this.setSrcID(in.getSrcID());
        this.setSource(in.getSource());
        this.setNote(in.getNote());
        this.setStatusID(in.getStatusID());
        this.setStatusName(in.getStatusName());
        this.setTDate(in.getTDate());
        this.setTSDate(in.getTSDate());
        this.setOperator(in.getOperator());
        this.setNDate(in.getNDate());
        this.setNSDate(in.getNSDate());
        this.setcNote(in.getcNote());
        this.setPeriod(in.getPeriod());
        
        this.setProduct(in.getProduct());
        
        this.setMDate(in.getMDate());
        this.setMSDate(in.getMSDate());
    }
    
    // for updating (clear on close)
    public void rewrite(ReqG in) { 
        this.setReqID(in.getReqID());
        this.setResID(in.getResID());
        this.setFrmName(in.getFrmName());
        this.setCDate(in.getCDate());
        this.setCSDate(in.getCSDate());
        this.setLName(in.getLName());
        this.setFName(in.getFName());
        this.setSName(in.getSName());
        this.setPhone(in.getPhone());
        this.setEmaill(in.getEmaill());
        this.setRegID(in.getRegID());
        this.setRegion(in.getRegion());
        this.setProID(in.getProID());
        this.setProduct(in.getProduct());
        this.setSrcID(in.getSrcID());
        this.setSource(in.getSource());
        this.setNote(in.getNote());
        this.setStatusID(in.getStatusID());
        this.setStatusName(in.getStatusName());
        this.setTDate(in.getTDate());
        this.setTSDate(in.getTSDate());
        this.setOperator(in.getOperator());
        this.setNDate(in.getNDate());
        this.setNSDate(in.getNSDate());
        this.setcNote(in.getcNote());
        this.setPeriod(in.getPeriod());
        
        this.setIsNew(in.getIsNew());
        this.setPerID(in.getPerID());
        this.setTrfID(in.getTrfID());
        this.setCurID(in.getCurID());
        this.setVal(in.getTerm());
        this.setOffID(in.getOffID());
        this.setTypeID(in.getTypeID());
        
        this.setProduct(in.getProduct());
        
        this.setMDate(in.getMDate());
        this.setMSDate(in.getMSDate());
        
        this.setHphone(in.getHphone());
        this.setWphone(in.getWphone());
        this.setBDate(in.getBDate());
        this.setIsRes(in.isIsRes());
        this.setWregion(in.getWregion());
        this.setWregID(in.getWregID());
        this.setConfirm(in.getConfirm());
        this.setTSeniority(TSeniority);
        this.setCSeniority(CSeniority);
        
    }
    
}
