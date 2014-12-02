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

@ManagedBean
@SessionScoped
public class TRegionBean implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private List<Region> regList;
        
        private List<String> regStringList;
        
        private ResultSet result;
	private Statement stmt;
	private Connection conn;
	
	public TRegionBean(){
                         regList = new ArrayList<Region>();
                         regStringList  = new ArrayList<String>();
                         populateRegList(regList, regStringList);
	}

	private void populateRegList(List<Region> list, List<String> stringList) {
        try {
           
            
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				conn = ds.getConnection();
				stmt = conn.createStatement();
				result = null;
				if (stmt.execute("exec [dbo].[spRegion_s]")){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spRegion_s]!");
				
			    while (result.next()){ 
			    	Region row = new Region(result.getInt(1),result.getString(2));
			    	
                                list.add(row);
                                stringList.add(result.getString(2));
                                
			    };
            
        } catch (SQLException ex) {
            Logger.getLogger(TRegionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TRegionBean.class.getName()).log(Level.SEVERE, null, ex);
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

	public List<Region> getRegList() {		
		return regList;
	}      

    public List<String> getRegStringList() {
        return regStringList;
    }

    public void setRegStringList(List<String> regStringList) {
        this.regStringList = regStringList;
    }
        
}
