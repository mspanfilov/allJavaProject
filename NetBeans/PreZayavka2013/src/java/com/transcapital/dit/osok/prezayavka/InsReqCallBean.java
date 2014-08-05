package com.transcapital.dit.osok.prezayavka;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@ManagedBean
@RequestScoped
public class InsReqCallBean implements Serializable {
    
    private Call insertedCall = new Call();
    
    private ReqG insertedReq = new ReqG();
    
    @ManagedProperty(value="#{tRequestBean.selectedReqG}")
    private ReqG selectedReqG = new ReqG();
    
    @ManagedProperty(value="#{tCallStatusBean.callStatusList}")
    private List<CallStatus> callStatusList = new ArrayList<CallStatus>();
    
    private CallableStatement stmtTemp;
    private Connection connTemp;
    
    // for inserting (clear on close)
    @ManagedProperty(value="#{technicalSessionBean.FDate}")
    private Timestamp FDateS;
    
    
        @ManagedProperty(value="#{tRegionBean.regList}")
        private List<Region> regList = new ArrayList<Region>();
        
        @ManagedProperty(value="#{tProductBean.proList}")
        private List<Product> proList = new ArrayList<Product>();
        
        //@ManagedProperty(value="#{tReqStatusBean.reqStatusList}")
        //private List<ReqStatus> reqStatusList = new ArrayList<>();
        
        @ManagedProperty(value="#{tSourceBean.sourceList}")
        private List<Source> sourceList = new ArrayList<Source>();
        

    public InsReqCallBean() {

    }

    public Call getInsertedCall() {
        return insertedCall;
    }

    public void setInsertedCall(Call insertedCall) {
        this.insertedCall = insertedCall;
    }

    public ReqG getInsertedReq() {
        return insertedReq;
    }

    public void setInsertedReq(ReqG insertedReq) {
        this.insertedReq = insertedReq;
    }

    
    public void setSelectedReqG(ReqG selectedReqG) {
        this.selectedReqG = selectedReqG;
    }

    
    public void setCallStatusList(List<CallStatus> callStatusList) {
        this.callStatusList = callStatusList;
    }
    
        // finding StatusID for autosave in DB
        private void findStatusID(){            
            for(int i=0; i<callStatusList.size(); i++){
                if (callStatusList.get(i).getCallStatusName().equals(insertedCall.getStatusName())){
                    insertedCall.setStatusID(callStatusList.get(i).getCallStatusID());
                    break;
                }
            }
        }
    
        // inserting tCall in DB
        public void insertCall() {
        try {
            
            findStatusID();
            
                // get Requesr for get User
                HttpServletRequest request = (HttpServletRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest();            
                        
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				connTemp = ds.getConnection();
				stmtTemp = connTemp.prepareCall("{ call [dbo].[spCall_iu](null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

                                stmtTemp.setInt(1, selectedReqG.getReqID());
                                stmtTemp.setTimestamp(2, (java.sql.Timestamp) FDateS);
				stmtTemp.setString(3, "TCB\\" + request.getRemoteUser());                                                      //!!!!!!!!!
                                stmtTemp.setString(4, insertedCall.getNote());     
                                if (insertedCall.getNDate() == null){
                                    stmtTemp.setNull(5, 0);
                                }else{
                                    stmtTemp.setDate(5,new Date(insertedCall.getNDate().getTime()));
                                }
                                stmtTemp.setString(6, insertedCall.getStatusID());
                                
                                stmtTemp.setString(7, selectedReqG.getLName());
                                stmtTemp.setString(8, selectedReqG.getFName());
                                stmtTemp.setString(9, selectedReqG.getSName());
                                stmtTemp.setString(10, selectedReqG.getPhone());
                                stmtTemp.setString(11, selectedReqG.getEmaill());
                                stmtTemp.setInt(12, selectedReqG.getRegID());
                                stmtTemp.setString(13, selectedReqG.getRegion());
                                stmtTemp.setInt(14, selectedReqG.getProID());
                                stmtTemp.setInt(15, selectedReqG.getSrcID());
                                stmtTemp.setString(16, selectedReqG.getSource());
                                                               
                                stmtTemp.executeUpdate();
                                
        // for inserting (clear on close) only display                            
        clearTimerCall();                        
                                                                           
        } catch (SQLException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
 		stmtTemp.close(); 
 		connTemp.close();
            } catch (SQLException ex) {
                Logger.getLogger(TCallBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }

    public void setFDateS(Timestamp FDateS) {
        this.FDateS = FDateS;
    }

        // for inserting (clear on close) only display
        public void clearTimerCall(){
            insertedCall = null;
            insertedReq = null;
        }
        
        // for inserting (clear on close) only display
        public void showTimerCall(){
        insertedCall.setFDate(FDateS);
        
        Locale loc = new Locale("ru","RU");
        DateFormat dfShort = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, loc);
        
        insertedCall.setFSDate(dfShort.format(insertedCall.getFDate()));
        
        }
        
        
        
        
        
        public void setRegList(List<Region> regList) {
		this.regList = regList;
	}
        
        public void setProList(List<Product> proList) {
		this.proList = proList;
	}
        
        //public void setReqStatusList(List<ReqStatus> reqStatusList) {
	//	this.reqStatusList = reqStatusList;
	//}
        
        public void setSourceList(List<Source> sourceList) {
		this.sourceList = sourceList;
	}
        
        // finding RegID for autosave in DB
        private void findRegID(){            
            for(int i=0; i<regList.size(); i++){
                if (regList.get(i).getRegName().equals(insertedReq.getRegion())){
                    insertedReq.setRegID(regList.get(i).getRegID());
                    break;
                }else{
                    insertedReq.setRegID(-1);
                }
            }
        }
        
        // finding ProID for autosave in DB
        private void findProID(){            
            for(int i=0; i<proList.size(); i++){
                if (proList.get(i).getProName().equals(insertedReq./*getProduct*/getSource())){
                    insertedReq.setProID(proList.get(i).getProID());
                    break;
                }else{
                    insertedReq.setProID(-1);
                }
            }
        }
        
        // finding SrcID for autosave in DB
        private void findSrcID(){            
            for(int i=0; i<sourceList.size(); i++){
                if (sourceList.get(i).getSrcName().equals(insertedReq.getRegion())){
                    insertedReq.setRegID(sourceList.get(i).getSrcID());
                    break;
                }else{
                    insertedReq.setSrcID(-1);
                }
            }
        }
        
        // inserting Call and Req in DB
        public void insertReqCall() {
        try {
            
            findStatusID();
                        
            findRegID();
            findProID();
            findStatusID();
            findSrcID();
            
                // get Requesr for get User
                HttpServletRequest request = (HttpServletRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest(); 
                        
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				connTemp = ds.getConnection();
				stmtTemp = connTemp.prepareCall("{ call [dbo].[spCall_iu](null,null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

                                stmtTemp.setTimestamp(1, (java.sql.Timestamp) FDateS);
				stmtTemp.setString(2, "TCB\\" + request.getRemoteUser());                                                      //!!!!!!!!!
                                stmtTemp.setString(3, insertedCall.getNote());     
                                if (insertedCall.getNDate() == null){
                                    stmtTemp.setNull(4, 0);
                                }else{
                                    stmtTemp.setDate(4,new Date(insertedCall.getNDate().getTime()));
                                }
                                stmtTemp.setString(5, insertedCall.getStatusID());
                                
                                stmtTemp.setString(6, insertedReq.getLName());
                                stmtTemp.setString(7, insertedReq.getFName());
                                stmtTemp.setString(8, insertedReq.getSName());
                                stmtTemp.setString(9, insertedReq.getPhone());
                                stmtTemp.setString(10, insertedReq.getEmaill());
                                stmtTemp.setInt(11, insertedReq.getRegID());
                                stmtTemp.setString(12, insertedReq.getRegion());
                                stmtTemp.setInt(13, insertedReq.getProID());
                                stmtTemp.setInt(14, insertedReq.getSrcID());
                                stmtTemp.setString(15, insertedReq.getSource());
                                                               
                                stmtTemp.executeUpdate();
                                
        // for inserting (clear on close) only display                            
        clearTimerCall();                        
                                                                           
        } catch (SQLException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
 		stmtTemp.close(); 
 		connTemp.close();
            } catch (SQLException ex) {
                Logger.getLogger(TCallBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }

        
}
