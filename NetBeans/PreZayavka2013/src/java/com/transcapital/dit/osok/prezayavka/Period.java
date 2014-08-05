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
public class Period implements Serializable {
    
    	private String perID;
	private String perName;

    public Period(String perID, String perName) {
        this.perID = perID;
        this.perName = perName;
    }

    public String getPerID() {
        return perID;
    }

    public void setPerID(String perID) {
        this.perID = perID;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }
    
        
        
}
