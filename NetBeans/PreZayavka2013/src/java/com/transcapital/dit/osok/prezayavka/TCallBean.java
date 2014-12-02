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
//import sun.security.krb5.Realm;


@ManagedBean
@RequestScoped
public class TCallBean implements Serializable, ActionListener{


	private static final long serialVersionUID = 1L;
        
	private List<Call> callList;
        
        private Call selectedCall;
        
        @ManagedProperty(value="#{tRequestBean.selectedReq}")
        private Req selectedReq = new Req();
           
        private ResultSet result;
	private Statement stmt;
	private Connection conn;

        
	public TCallBean(){
                         callList = new ArrayList<Call>();
                         if (selectedReq != null){
                            populateCallList(callList); 
                         }
	}

	private void populateCallList(List<Call> list) {  
        try {

            InitialContext ic = new InitialContext();
            
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
                                
				conn = ds.getConnection();
				stmt = conn.createStatement();
				result = null;
                                      
				if (stmt.execute("exec [dbo].[spCall_s]" + selectedReq.getReqID())){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spCall_s]!");
				
                                Locale loc = new Locale("ru","RU");
                                DateFormat dfShort = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, loc);
                                DateFormat dfShortDate = DateFormat.getDateInstance(DateFormat.SHORT, loc);
                                
                                while (result.next()){ 
                                    Call row = new Call(result.getInt(1), result.getTimestamp(2), result.getTimestamp(2)==null?null:dfShort.format(result.getTimestamp(2)), 
                                                    result.getTimestamp(3), result.getTimestamp(3)==null?null:dfShort.format(result.getTimestamp(3)), result.getInt(4), result.getString(5), 
                                                    result.getString(6), result.getDate(7), result.getDate(7)==null?null:dfShortDate.format(result.getDate(7)), result.getString(8), result.getString(9));
			    	
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
       
	public List<Call> getCallList() {
		return callList;
	}
        
        public Call getSelectedCall() {
		return selectedCall;
	}
	public void setSelectedCall(Call selectedCall) {
		this.selectedCall = selectedCall;
	}
        
        public Req getSelectedReq() {
                return selectedReq;
        }
        
        public void setSelectedReq(Req selectedReq) {
                this.selectedReq = selectedReq;
                if (selectedReq != null){
                    populateCallList(callList);
                }
        }
        
        // refreshing main page(CallList)
        public void refresh(){ 
            selectedCall = null;
            callList = new ArrayList<Call>();
            if (selectedReq != null){
            populateCallList(callList);
            }
        }
        
        // clear main page(CallList)
        public void clear(){
            selectedReq = null;
            selectedCall = null;
            callList = new ArrayList<Call>();
        }

    @Override
    public void processAction(ActionEvent ae) {
        refresh();
    }
        
    
}
