/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class TechnicalSessionBean implements Serializable {
    
    private Timestamp fDate;
    private String currentUser;
    private String currentPassword;

    public TechnicalSessionBean() {

    }

    public Timestamp getFDate() {
        return fDate;
    }

    public void setFDate(Timestamp fDate) {
        this.fDate = fDate;
    }
    
    public void startTimerCall(){    
        fDate = new Timestamp(System.currentTimeMillis());
    }
    
    public void clearTimerCall(){ 
        fDate = null;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
    
            public void refresh(){                
                // get User
                HttpServletRequest request = (HttpServletRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest();            
                setCurrentUser(request.getRemoteUser());
            }
    
}
