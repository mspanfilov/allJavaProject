/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author panfilov_ms
 */
@ManagedBean
@SessionScoped
public class TOfficeBean implements Serializable {
    
        private static final long serialVersionUID = 1L;	
    
    	private List<Office> offList;
        private List<String> offStringList;
        
        private ResultSet result;
	private Statement stmt;
	private Connection conn;

    public TOfficeBean() {
        
        offList = new ArrayList<Office>();
        offStringList  = new ArrayList<String>();
        populateOffList(offList, offStringList);
        
    }
        
        
        
    private void populateOffList(List<Office> list, List<String> stringList) {
        try {
           
            
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				conn = ds.getConnection();
				stmt = conn.createStatement();
				result = null;
				if (stmt.execute("exec [dbo].[spOffice_s]")){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spOffice_s]!");
				
			    while (result.next()){ 
			    	Office row = new Office(result.getInt(1),result.getString(2).replaceFirst("    ", "....."),result.getString(3),result.getString(2).trim());
			    	
                                list.add(row);
                                stringList.add(result.getString(2));
                                
			    }
            
        } catch (SQLException ex) {
            Logger.getLogger(TRegionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TRegionBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                result.close();
 		stmt.close(); 
 		conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TCallBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}

    public List<Office> getOffList() {
        return offList;
    }

    public List<String> getOffStringList() {
        return offStringList;
    }

    public void setOffStringList(List<String> offStringList) {
        this.offStringList = offStringList;
    }
    
}
