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
public class Currency implements Serializable {
    
    	private String curID;
	private String curName;

    public Currency(String curID, String curName) {
        this.curID = curID;
        this.curName = curName;
    }

    public String getCurID() {
        return curID;
    }

    public void setCurID(String curID) {
        this.curID = curID;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }        
    
}
