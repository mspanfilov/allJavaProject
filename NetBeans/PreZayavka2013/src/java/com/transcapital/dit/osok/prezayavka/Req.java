package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Req implements Serializable {
	
	private int reqID;
	private int resID;
        private String frmName;
	private Timestamp CDate;
        private String CSDate;
	private String LName;
	private String FName;
	private String SName;
	private String phone;
	private String emaill;
    private int regID;
	private String region;
    private int proID;
	private String product;
    private int srcID;
	private String source;
    private String note;
	private String statusID;
    private String statusName;
    private Date TDate;
    private String TSDate;
    private String operator;
    private Date NDate;
    private String NSDate;
    private String cNote;
    private String cStatusName;    

    /*private String isNew;
    private String perID;
    private String trfID;
    private String curID;
    private int val;
    private int term;
    private int offID;
    private int typeID;*/
    
    private String period;
    
    private Date MDate;
    private String MSDate;
	
    public Req(int reqID, int resID, String frmName, Timestamp CDate, String CSDate, String LName,
			String FName, String SName, String phone, String emaill, int regID,
			String region, int proID, String product, int srcID, String source,
			String note, String statusID, String statusName, Date TDate, String TSDate,
			String operator, Date NDate, String NSDate, String cNote, String cStatusName/*, 
                        String isNew, String perID, String trfID, String curID, int val, int term, int offID, int typeID*/, String period, Date MDate, String MSDate) {
		super();
		this.reqID = reqID;
		this.resID = resID;
		this.frmName = frmName;
		this.CDate = CDate;
                this.CSDate = CSDate;
		this.LName = LName;
		this.FName = FName;
		this.SName = SName;
		this.phone = phone;
		this.emaill = emaill;
		this.regID = regID;
		this.region = region;
		this.proID = proID;
		this.product = product;
		this.srcID = srcID;
		this.source = source;
		this.note = note;
		this.statusID = statusID;
		this.statusName = statusName;
		this.TDate = TDate;
                this.TSDate = TSDate;
		this.operator = operator;
		this.NDate = NDate;
                this.NSDate = NSDate;
		this.cNote = cNote;
		this.cStatusName = cStatusName;
                this.NDate = NDate;
                this.NSDate = NSDate;
                
                /*this.isNew = isNew;
                this.perID = perID;

                this.trfID = trfID;
                this.curID = curID;
                this.val = val;
                this.term = term;
                this.offID = offID;
                this.typeID = typeID;*/
                
                this.period = period;
                this.MDate = MDate;
                this.MSDate = MSDate;
                
	}

    Req() {
  
    }

    public Timestamp getCDate() {
        return CDate;
    }

    public void setCDate(Timestamp CDate) {
        this.CDate = CDate;
    }

    public String getCSDate() {
        return CSDate;
    }

    public void setCSDate(String CSDate) {
        this.CSDate = CSDate;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getcNote() {
        return cNote;
    }

    public void setcNote(String cNote) {
        this.cNote = cNote;
    }

    public String getcStatusName() {
        return cStatusName;
    }

    public void setcStatusName(String cStatusName) {
        this.cStatusName = cStatusName;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public String getFrmName() {
        return frmName;
    }

    public void setFrmName(String frmName) {
        this.frmName = frmName;
    }

    public Date getNDate() {
        return NDate;
    }

    public void setNDate(Date NDate) {
        this.NDate = NDate;
    }

    public String getNSDate() {
        return NSDate;
    }

    public void setNSDate(String NSDate) {
        this.NSDate = NSDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getRegID() {
        return regID;
    }

    public void setRegID(int regID) {
        this.regID = regID;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getReqID() {
        return reqID;
    }

    public void setReqID(int reqID) {
        this.reqID = reqID;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSrcID() {
        return srcID;
    }

    public void setSrcID(int srcID) {
        this.srcID = srcID;
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

    public Date getTDate() {
        return TDate;
    }

    public void setTDate(Date TDate) {
        this.TDate = TDate;
    }

    public String getTSDate() {
        return TSDate;
    }

    public void setTSDate(String TSDate) {
        this.TSDate = TSDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
    
    
    
        
        /*
DROP TABLE IF EXISTS tRequest ;

CREATE TABLE tRequest  ( 
    reqID   	int IDENTITY(1,1) NOT NULL,
    resID   	int NULL,
   frmName   nvarchar(50) NULL,
    cDate   	datetime NULL,
    lName   	nvarchar(50) NULL,
    fName   	nvarchar(50) NULL,
    sName  	nvarchar(50) NULL,
    phone   	nvarchar(30) NULL,
    email   	nvarchar(50) NULL,
    regID   	int NULL,
    region  	nvarchar(100) NULL,
    proID   	int NULL,
    product   	nvarchar(50) NULL,
    srcID   	int NULL,
    source  	nvarchar(255) NULL,
    note    	nvarchar(max) NULL,
    statusID char(1) NULL, 
    statusname   nvarchar(50) NULL,
    tDate   	datetime NULL,
    operator  nvarchar(50) NULL,
    nDate   	datetime NULL,
    cnote  nvarchar(50) NULL,
    cstatusname  nvarchar(50) NULL
    );

INSERT INTO tRequest(reqID, resID, cDate, frmName, lName) 
    VALUES(30, 0, '2012-5-4 15:37:16', null, 'qwe');

INSERT INTO tRequest(reqID, resID, cDate, frmName, lName) 
    VALUES(31, 1, '2012-5-4 16:37:16', null, 'eqw ');


select * from trequest;
         */

    public Date getMDate() {
        return MDate;
    }

    public void setMDate(Date MDate) {
        this.MDate = MDate;
    }

    public String getMSDate() {
        return MSDate;
    }

    public void setMSDate(String MSDate) {
        this.MSDate = MSDate;
    }
}
