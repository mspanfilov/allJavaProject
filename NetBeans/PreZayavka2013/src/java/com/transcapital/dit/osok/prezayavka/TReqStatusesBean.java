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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author panfilov_ms
 */
@ManagedBean
@RequestScoped
public class TReqStatusesBean implements Serializable, ActionListener {
    
    	private List<ReqStatuses> reqStatusesList;
        
        private ReqStatuses selectedReqStatuses;
        
        @ManagedProperty(value="#{tRequestBean.selectedReq}")
        private Req selectedReq = new Req();
           
        private ResultSet result;
	private Statement stmt;
	private Connection conn;

    public TReqStatusesBean() {
        
        reqStatusesList = new ArrayList<ReqStatuses>();
        if (selectedReq != null){
            populateReqStatusesList(reqStatusesList); 
        }
    }
    
    private void populateReqStatusesList(List<ReqStatuses> list) {  
        try {
            
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				conn = ds.getConnection();
				stmt = conn.createStatement();
				result = null;
                                      
				if (stmt.execute("exec [dbo].[spStatus_s]" + selectedReq.getReqID())){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spStatus_s]!");
				
                                Locale loc = new Locale("ru","RU");
                                DateFormat dfShort = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, loc);
                                DateFormat dfShortDate = DateFormat.getDateInstance(DateFormat.SHORT, loc);
                                
                                while (result.next()){ 
                                    ReqStatuses row = new ReqStatuses(result.getInt(1), result.getInt(2), result.getString(3), result.getInt(4),
                                                    result.getTimestamp(5), result.getTimestamp(5)==null?null:dfShort.format(result.getTimestamp(5)), 
                                                    result.getString(6), result.getDate(7), result.getDate(7)==null?null:dfShortDate.format(result.getDate(7)),
                                                    result.getInt(8), result.getString(9), result.getString(10), result.getString(11), result.getString(12),
                                                    result.getString(13), result.getString(14), result.getString(15), result.getString(15).equals("Y")?true:false, 
                                                    result.getDate(16), result.getDate(16)==null?null:dfShort.format(result.getDate(16)));
			    	
                                    list.add(row);
                                }
            
        } catch (SQLException ex) {
            Logger.getLogger(TCallBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TCallBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<ReqStatuses> getReqStatusesList() {
        return reqStatusesList;
    }

    public ReqStatuses getSelectedReqStatuses() {
        return selectedReqStatuses;
    }

    public void setSelectedReqStatuses(ReqStatuses selectedReqStatuses) {
        this.selectedReqStatuses = selectedReqStatuses;
    }

    public Req getSelectedReq() {
        return selectedReq;
    }

    public void setSelectedReq(Req selectedReq) {
        this.selectedReq = selectedReq;
                if (selectedReq != null){
                    populateReqStatusesList(reqStatusesList);
                }
    }
    
    // refreshing main page(CallList)
        public void refresh(){ 
            selectedReqStatuses = null;
            reqStatusesList = new ArrayList<ReqStatuses>();
            if (selectedReq != null){
            populateReqStatusesList(reqStatusesList);
            }
        }
        
        // clear main page(CallList)
        public void clear(){
            selectedReq = null;
            selectedReqStatuses = null;
            reqStatusesList = new ArrayList<ReqStatuses>();
        }

    @Override
    public void processAction(ActionEvent ae) {
        refresh();
    }
        
         
}
