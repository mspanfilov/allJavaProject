package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;

public class Product implements Serializable {
    
    	private int proID;
	private String proNameTrim;
        private String proName;
	
	public Product(int proID, String proNameTrim, String proName) {
		super();
		this.proID = proID;
                this.proNameTrim = proNameTrim;
		this.proName = proName;
	}
	
	public int getProID() {
		return proID;
	}
	public void setProID(int proID) {
		this.proID = proID;
	}
        
        public String getProNameTrim() {
            return proNameTrim;
        }

        public void setProNameTrim(String proNameTrim) {
            this.proNameTrim = proNameTrim;
        }
        
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
        
        
        
    Product() {
  
    }
    
}
