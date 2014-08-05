package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;

public class Source implements Serializable {
    
    	private int srcID;
	private String srcNameTrim;
        private String srcName;

    public Source(int srcID, String srcNameTrim, String srcName) {
        super();
        this.srcID = srcID;
        this.srcNameTrim = srcNameTrim;
        this.srcName = srcName;
    }
        
    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public int getSrcID() {
        return srcID;
    }

    public void setSrcID(int srcID) {
        this.srcID = srcID;
    }

    public String getSrcNameTrim() {
        return srcNameTrim;
    }

    public void setSrcNameTrim(String srcNameTrim) {
        this.srcNameTrim = srcNameTrim;
    }
    
    Source() {
  
    }
    
}
