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
public class TSourceBean implements Serializable{
    
    	private static final long serialVersionUID = 1L;	
	
	private List<Source> sourceList;
        
        private ResultSet result;
	private Statement stmt;
	private Connection conn;
        	
	public TSourceBean(){
                         sourceList = new ArrayList<Source>();
                         populateSourceList(sourceList);
	}

	private void populateSourceList(List<Source> list) {
        try {
            
            
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				conn = ds.getConnection();
				stmt = conn.createStatement();
				result = null;
				if (stmt.execute("exec [dbo].[spSource_s]")){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spSource_s]!");
				
			    while (result.next()){ 
			    	Source row = new Source(result.getInt(1),result.getString(2).trim(),result.getString(3).replaceFirst("    ", "....."));
			    	
                                list.add(row);     
			    }
            
        } catch (SQLException ex) {
            Logger.getLogger(TSourceBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TSourceBean.class.getName()).log(Level.SEVERE, null, ex);
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

	public List<Source> getSourceList() {		
		return sourceList;
	}
       
}
