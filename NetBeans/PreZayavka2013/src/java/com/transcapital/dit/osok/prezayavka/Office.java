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
public class Office implements Serializable {
    
    	private int offID;
	private String offName;
        private String address;
        private String offNameTrim;

    public Office(int offID, String offName, String address, String offNameTrim) {
        this.offID = offID;
        this.offName = offName;
        this.address = address;
        this.offNameTrim = offNameTrim;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOffID() {
        return offID;
    }

    public void setOffID(int offID) {
        this.offID = offID;
    }

    public String getOffName() {
        return offName;
    }

    public void setOffName(String offName) {
        this.offName = offName;
    }

    public String getOffNameTrim() {
        return offNameTrim;
    }

    public void setOffNameTrim(String offNameTrim) {
        this.offNameTrim = offNameTrim;
    }
    
        
        
}
