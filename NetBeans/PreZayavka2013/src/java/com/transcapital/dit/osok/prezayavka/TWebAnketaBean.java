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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author panfilov_ms
 */
@ManagedBean
@RequestScoped
public class TWebAnketaBean implements Serializable {
    
    private List<WebAnketa> webAnketaList;
    
    @ManagedProperty(value="#{tRequestBean.selectedReq}")
    private Req selectedReq = new Req();
    
    private ResultSet result;
    private Statement stmt;
    private Connection conn;

    public TWebAnketaBean() {
        webAnketaList = new ArrayList<WebAnketa>();
        if (selectedReq != null){
            populateWebAnketaList(webAnketaList); 
        }
    }
    
    	private void populateWebAnketaList(List<WebAnketa> list) {  
        try {

            InitialContext ic = new InitialContext();
            
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
                                
				conn = ds.getConnection();
				stmt = conn.createStatement();
				result = null;
                                      
				if (stmt.execute("exec [dbo].[spWebValue_s]" + selectedReq.getReqID())){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spWebValue_s]!");
                                
                                while (result.next()){ 
                                    WebAnketa row = new WebAnketa(result.getInt(1), result.getInt(2),
                                                    result.getString(3), result.getString(4), result.getString(5), result.getString(6));
			    	
                                    list.add(row);
                                }
            
        } catch (SQLException ex) {
            Logger.getLogger(TWebAnketaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TWebAnketaBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                result.close();
 		stmt.close(); 
 		conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TWebAnketaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}

    public List<WebAnketa> getWebAnketaList() {
        return webAnketaList;
    }  
        
    public Req getSelectedReq() {
        return selectedReq;
    }

    public void setSelectedReq(Req selectedReq) {
        this.selectedReq = selectedReq;
        if (selectedReq != null){
            populateWebAnketaList(webAnketaList);
        }
    }
        
        
    
    
}
