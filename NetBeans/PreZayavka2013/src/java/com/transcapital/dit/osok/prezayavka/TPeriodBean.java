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
public class TPeriodBean implements Serializable {
    
        private static final long serialVersionUID = 1L;	
	
	private List<Period> perList;
        private List<String> perStringList;
        
        private ResultSet result;
	private Statement stmt;
	private Connection conn;
        	
	public TPeriodBean(){
                         perList = new ArrayList<Period>();
                         perStringList = new ArrayList<String>();
                         populatePerList(perList, perStringList);
	}

	private void populatePerList(List<Period> list, List<String> stringList) {
        try {
            
            
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				conn = ds.getConnection();
				stmt = conn.createStatement();
				result = null;
				if (stmt.execute("exec [dbo].[spPeriod_s]")){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spPeriod_s]!");
				
			    while (result.next()){ 
			    	Period row = new Period(result.getString(1),result.getString(2));
			    	
                                list.add(row);
                                stringList.add(result.getString(2));
                                
			    }
            
        } catch (SQLException ex) {
            Logger.getLogger(TProductBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TProductBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (result != null) { 
                    result.close();
                }
                if (stmt != null) { 
                    stmt.close(); 
                }
                if (conn != null) { 
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TCallBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}

	public List<Period> getPerList() {		
		return perList;
	}

    public List<String> getPerStringList() {
        return perStringList;
    }

    public void setPerStringList(List<String> perStringList) {
        this.perStringList = perStringList;
    }
    
    
}
