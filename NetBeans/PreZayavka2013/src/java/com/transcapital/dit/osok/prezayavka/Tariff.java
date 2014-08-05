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
public class Tariff implements Serializable {
    
    	private String trfID;
	private String trfName;

    public Tariff(String trfID, String trfName) {
        this.trfID = trfID;
        this.trfName = trfName;
    }

    public String getTrfID() {
        return trfID;
    }

    public void setTrfID(String trfID) {
        this.trfID = trfID;
    }

    public String getTrfName() {
        return trfName;
    }

    public void setTrfName(String trfName) {
        this.trfName = trfName;
    }        
        
}
