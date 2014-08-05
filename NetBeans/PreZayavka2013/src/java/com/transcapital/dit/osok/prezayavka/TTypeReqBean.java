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
public class TTypeReqBean implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private List<TypeReq> typeList;
        
        private List<String> typeStringList;
        
        private ResultSet result;
	private Statement stmt;
	private Connection conn;
	
	public TTypeReqBean(){
                         typeList = new ArrayList<TypeReq>();
                         typeStringList  = new ArrayList<String>();
                         populateTypeList(typeList, typeStringList);
	}

	private void populateTypeList(List<TypeReq> list, List<String> stringList) {
        try {
           
            
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				conn = ds.getConnection();
				stmt = conn.createStatement();
				result = null;
				if (stmt.execute("exec [dbo].[spReqType_s]")){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spReqType_s]!");
				
			    while (result.next()){ 
			    	TypeReq row = new TypeReq(result.getInt(1),result.getString(2));
			    	
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

	public List<TypeReq> getTypeList() {		
		return typeList;
	}      

    public List<String> getTypeStringList() {
        return typeStringList;
    }

    public void setTypeStringList(List<String> typeStringList) {
        this.typeStringList = typeStringList;
    }
    
}
