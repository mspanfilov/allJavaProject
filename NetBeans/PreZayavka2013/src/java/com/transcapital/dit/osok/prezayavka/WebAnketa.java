/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.prezayavka;

/**
 *
 * @author panfilov_ms
 */
public class WebAnketa {
    
    private int resID;
    private int fldID;
    private String fldName;
    private String val;
    private String fldType;
    private String ansType;

    public WebAnketa(int resID, int fldID, String fldName, String val, String fldType, String ansType) {
        this.resID = resID;
        this.fldID = fldID;
        this.fldName = fldName;
        this.val = val;
        this.fldType = fldType;
        this.ansType = ansType;
    }

    public WebAnketa() {
    }

    public String getAnsType() {
        return ansType;
    }

    public void setAnsType(String ansType) {
        this.ansType = ansType;
    }

    public int getFldID() {
        return fldID;
    }

    public void setFldID(int fldID) {
        this.fldID = fldID;
    }

    public String getFldName() {
        return fldName;
    }

    public void setFldName(String fldName) {
        this.fldName = fldName;
    }

    public String getFldType() {
        return fldType;
    }

    public void setFldType(String fldType) {
        this.fldType = fldType;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
    
    
}
