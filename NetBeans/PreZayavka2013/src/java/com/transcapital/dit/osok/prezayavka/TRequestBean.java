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
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


@ManagedBean
@SessionScoped
public class TRequestBean implements Serializable, ActionListener{
    
	private static final long serialVersionUID = 1L;
        
	private List<Req> reqList;
        
        @ManagedProperty(value="#{tPeriodBean.perList}")
        private List<Period> perList = new ArrayList<Period>();
        
        private Req selectedReq;
        private ReqG selectedReqG = new ReqG();
        
        // for updating (clear on close)
        private ReqG selectedReqS = new ReqG();
        
        private CallableStatement stmtTemp;
	private Connection connTemp;
        
        private Statement stmtTemp2;
	private Connection connTemp2;
    
        private String selectedReg = new String();
        private String selectedWReg = new String();
        @ManagedProperty(value="#{tRegionBean.regList}")
        private List<Region> regList = new ArrayList<Region>();
        
        private String selectedPro = new String();
        @ManagedProperty(value="#{tProductBean.proList}")
        private List<Product> proList = new ArrayList<Product>();
        
        @ManagedProperty(value="#{tReqStatusBean.reqStatusList}")
        private List<ReqStatus> reqStatusList = new ArrayList<ReqStatus>();
        
        private String selectedSrc = new String();
        @ManagedProperty(value="#{tSourceBean.sourceList}")
        private List<Source> sourceList = new ArrayList<Source>();
        
        private String selectedOff = new String();
        @ManagedProperty(value="#{tOfficeBean.offList}")
        private List<Office> offList = new ArrayList<Office>();
        
        private Filter filter = new Filter();

        private String selectedConfirm = new String();
        private String selectedTSeniority = new String();
        private String selectedCSeniority = new String();
        
        private String selectedTariff = new String();
        @ManagedProperty(value="#{tTariffBean.trfList}")
        private List<Tariff> trfList = new ArrayList<Tariff>();
        
        private String selectedCur = new String();
        @ManagedProperty(value="#{tCurrencyBean.curList}")
        private List<Currency> curList = new ArrayList<Currency>();

	public TRequestBean(){
                         reqList = new ArrayList<Req>();
                         filter = new Filter(new Date(System.currentTimeMillis()-86400000),(new Date(System.currentTimeMillis())));
                         // move "populateReqList(reqList);" to setPerList
                         //populateReqList(reqList);
	}

        private void populateReqListAll(List<Req> list) {
        try {
            
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				Connection conn = ds.getConnection();
                                
                                // вызов списка заявок через процедуру с параметрами (дата начала и дата окончания)
                                CallableStatement stmt = conn.prepareCall("exec [dbo].[spRequestAll_s] ?,?,?");
				ResultSet result = null;
                                
                                Locale loc = new Locale("ru","RU");
                                DateFormat dfShort = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, loc);
                                DateFormat dfShortDate = DateFormat.getDateInstance(DateFormat.SHORT, loc);
                                
                                //stmt.setDate(1, Date.valueOf("2013-05-05"));
                                //stmt.setDate(2, Date.valueOf("2014-05-05"));
                                stmt.setDate(1, new java.sql.Date(filter.getDateStart().getTime()));
                                stmt.setDate(2, new java.sql.Date(filter.getDateEnd().getTime()));
                             
                                stmt.setString(3, "N");
                                
				if (stmt.execute()){
					result = stmt.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spRequestAll_s]!");
                                
			    while (result.next()){ 
                                
                                Statement stmt2 = conn.createStatement();
                                ResultSet result2 = null;
                                
                                if (stmt2.execute("exec [dbo].[spRequest_g]" + result.getInt(1))){
					result2 = stmt2.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spRequest_g]!");
                                
                                while (result2.next()){ 
                                
			    	Req row = new Req(result.getInt(1), result.getInt(2),
                                                result.getString(3)==null?Const.NULL_STRING:result.getString(3),
                                                result.getTimestamp(4)==null?Const.MIN_TIMESTAMP:result.getTimestamp(4),result.getTimestamp(4)==null?null:dfShort.format(result.getTimestamp(4)), 
                                                result.getString(5)==null?Const.NULL_STRING:result.getString(5),
			    			result.getString(6)==null?Const.NULL_STRING:result.getString(6), 
                                                result.getString(7)==null?Const.NULL_STRING:result.getString(7), 
                                                result.getString(21)==null?Const.NULL_STRING:result.getString(21),
                                                result2.getString(8)==null?Const.NULL_STRING:result2.getString(8),
                                                result2.getInt(12), result.getString(8)==null?Const.NULL_STRING:result.getString(8), 
                                                result2.getInt(13), result.getString(17)==null?Const.NULL_STRING:result.getString(17), 
                                                result2.getInt(14), result.getString(16)==null?Const.NULL_STRING:result.getString(16), 
			    			result.getString(14)==null?Const.NULL_STRING:result.getString(14), 
                                                result2.getString(15)==null?Const.NULL_STRING:result2.getString(15),
                                                result.getString(13)==null?Const.NULL_STRING:result.getString(13),
                                        
                                                result.getTimestamp(11)==null?Const.MIN_DATE:result.getTimestamp(11),result.getTimestamp(11)==null?null:dfShort.format(result.getTimestamp(11)),

                                                result.getString(9)==null?Const.NULL_STRING:result.getString(9),
                                                result.getDate(12)==null?Const.MIN_DATE:result.getDate(12), result.getDate(12)==null?null:dfShortDate.format(result.getDate(12)), 
                                                result.getString(14)==null?Const.NULL_STRING:result.getString(14), 
                                                
                                                //надо вычислить cStatusName 
                                                Const.NULL_STRING,
                                                result2.getString(18)==null?Const.NULL_STRING:findPerName(result2.getString(18)),
                                                result.getTimestamp(22)==null?Const.MIN_DATE:result.getTimestamp(22),result.getTimestamp(22)==null?null:dfShort.format(result.getTimestamp(22))
                                        
                                                );
			    	
                                        list.add(row);
                                }
                                
			    }
                            
        } catch (SQLException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

        // refreshing main page (all)
        public void refresh(){
            
            selectedReq = null;
            selectedReqG = new ReqG();               //02/10/2012
            reqList = new ArrayList<Req>();
            populateReqListAll(reqList);
            
        }
        
        // updating tRequest in DB
        public void selectAddFieldForSelectedReqG() {
        try {
            InitialContext ic = new InitialContext();
                                DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				connTemp2 = ds.getConnection();
				stmtTemp2 = connTemp2.createStatement();
				ResultSet result = null;
                                
                                Locale loc = new Locale("ru","RU");
                                DateFormat dfShort = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, loc);
                                DateFormat dfShortDate = DateFormat.getDateInstance(DateFormat.SHORT, loc);
                                
                                if (stmtTemp2.execute("exec [dbo].[spRequest_g]" + selectedReqG.getReqID())){
					result = stmtTemp2.getResultSet();
				}
				else throw new SQLException("don't exec [dbo].[spRequest_g]!");
                                
                                while (result.next()){
                                selectedReqG.setRegion(result.getString(9)==null?Const.NULL_STRING:result.getString(9));
                                selectedReqG.setSource(result.getString(10)==null?Const.NULL_STRING:result.getString(10));
                                selectedReqG.setRegID(result.getInt(12));
                                selectedReqG.setProID(result.getInt(13));  
                                selectedReqG.setSrcID(result.getInt(14));  
                                selectedReqG.setIsNew(result.getString(16)==null?Const.NULL_STRING:result.getString(16));  
                                selectedReqG.setPerID(result.getString(18)==null?Const.NULL_STRING:result.getString(18));
                                selectedReqG.setTrfID(result.getString(19)==null?Const.NULL_STRING:result.getString(19));
                                selectedReqG.setCurID(result.getString(20)==null?Const.NULL_STRING:result.getString(20));
                                selectedReqG.setVal(result.getFloat(21));
                                selectedReqG.setTerm(result.getInt(22));
                                selectedReqG.setOffID(result.getInt(23));
                                selectedReqG.setFrmName(result.getString(24));
                                selectedReqG.setTypeID(result.getInt(25));
                                selectedReqG.setBDate(result.getTimestamp(28));
                                selectedReqG.setIsRes(result.getString(38)==null?false:result.getString(38).equals("Y")?true:false);
                                selectedReqG.setConfirm(result.getString(39)==null?"":result.getString(39).equals("T")?"2НДФЛ":result.getString(39).equals("B")?"Форма Банка":"");
                                selectedReqG.setWregion(result.getString(40)==null?Const.NULL_STRING:result.getString(40)); 
                                selectedReqG.setWregID(result.getInt(41)); 
                                selectedReqG.setTSeniority(result.getString(42)==null?"":result.getString(42).equals("Y")?"4 и более мес.":result.getString(42).equals("N")?"менее 4 мес.":"");
                                selectedReqG.setCSeniority(result.getString(43)==null?"":result.getString(43).equals("Y")?"12 и более мес.":result.getString(43).equals("N")?"менее 12 мес.":"");
                                selectedReqG.setHphone(result.getString(54)==null?Const.NULL_STRING:result.getString(54));
                                selectedReqG.setWphone(result.getString(55)==null?Const.NULL_STRING:result.getString(55));
                                
                                }
        } catch (SQLException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
 		stmtTemp2.close(); 
 		connTemp2.close();
            } catch (SQLException ex) {
                Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        
        // for updating (clear on close)
        public void saveSelectedReq(){
            selectedReqG.convert(selectedReq);
            // plus additional fields for ReqG
            selectAddFieldForSelectedReqG(); 
            selectedReqS.rewrite(selectedReqG);
            setComboBoxes(selectedReqG);
        }
        
        // for updating (clear on close)
        public void loadSelectedReq(){
            selectedReqG.rewrite(selectedReqS);
            setComboBoxes(selectedReqS);
        }
        
        // for setting values in ComboBoxes
        public void setComboBoxes(ReqG g){
            setSelectedPro(null);
            //setSelectedSrc(null);
            setSelectedOff(null);
            setSelectedReg(null);
            setSelectedWReg(null);
            setSelectedTariff(null);
            setSelectedCur(null);
                    
            for(int i=0; i<proList.size(); i++){
                if (proList.get(i).getProNameTrim().equals(g.getProduct())){
                    setSelectedPro(proList.get(i).getProName());
                    break;
                }
            }
            for(int i=0; i<sourceList.size(); i++){
                if (sourceList.get(i).getSrcNameTrim().equals(g.getSource())){
                    setSelectedSrc(sourceList.get(i).getSrcName());
                    break;
                }else{
                    //for settings values if it is not DB
                    setSelectedSrc(g.getSource());
                }
            }
            for(int i=0; i<offList.size(); i++){
                if (offList.get(i).getOffID() == (g.getOffID())){
                    setSelectedOff(offList.get(i).getOffName());
                    break;
                }
            }
            for(int i=0; i<regList.size(); i++){
                if (regList.get(i).getRegName().equals(g.getRegion())){
                    setSelectedReg(regList.get(i).getRegName());
                    break;
                }
            }
            for(int i=0; i<regList.size(); i++){
                if (regList.get(i).getRegName().equals(g.getWregion())){
                    setSelectedWReg(regList.get(i).getRegName());
                    break;
                }
            }
            setSelectedConfirm(g.getConfirm());
            setSelectedTSeniority(g.getTSeniority());
            setSelectedCSeniority(g.getCSeniority());
            for(int i=0; i<trfList.size(); i++){
                if (trfList.get(i).getTrfID().equals(g.getTrfID())){
                    setSelectedTariff(trfList.get(i).getTrfName());
                    break;
                }
            }
            for(int i=0; i<curList.size(); i++){
                if (curList.get(i).getCurID().equals(g.getCurID())){
                    setSelectedCur(curList.get(i).getCurName());
                    break;
                }
            }
        }
              
        public void setPerList(List<Period> perList) {
		this.perList = perList;               
                // move here, because @ManagedProperty loading later construction
                populateReqListAll(reqList);
	}

        public void setProList(List<Product> proList) {
            this.proList = proList;
        }
        
        public void setSourceList(List<Source> sourceList) {
            this.sourceList = sourceList;
	}
        
        public void setOffList(List<Office> offList) {
            this.offList = offList;
	}
        
        public void setRegList(List<Region> regList) {
		this.regList = regList;
	}

        public void setTrfList(List<Tariff> trfList) {
            this.trfList = trfList;
        }
        
        public void setCurList(List<Currency> curList) {
            this.curList = curList;
        }
        
        public void setReqStatusList(List<ReqStatus> reqStatusList) {
		this.reqStatusList = reqStatusList;
	}
        
        
        public String getSelectedPro() {
            return selectedPro;
        }

        public void setSelectedPro(String selectedPro) {
            this.selectedPro = selectedPro;
        }

        public String getSelectedSrc() {
            return selectedSrc;
        }

        public void setSelectedSrc(String selectedSrc) {
            this.selectedSrc = selectedSrc;
        }

        public String getSelectedOff() {
            return selectedOff;
        }

        public void setSelectedOff(String selectedOff) {
            this.selectedOff = selectedOff;
        }

        public String getSelectedReg() {
            return selectedReg;
        }

        public void setSelectedReg(String selectedReg) {
            this.selectedReg = selectedReg;
        }

        public String getSelectedWReg() {
            return selectedWReg;
        }

        public void setSelectedWReg(String selectedWReg) {
            this.selectedWReg = selectedWReg;
        }

        public String getSelectedCSeniority() {
            return selectedCSeniority;
        }

        public void setSelectedCSeniority(String selectedCSeniority) {
            this.selectedCSeniority = selectedCSeniority;
        }

        public String getSelectedConfirm() {
            return selectedConfirm;
        }

        public void setSelectedConfirm(String selectedConfirm) {
            this.selectedConfirm = selectedConfirm;
        }

        public String getSelectedTSeniority() {
            return selectedTSeniority;
        }

        public void setSelectedTSeniority(String selectedTSeniority) {
            this.selectedTSeniority = selectedTSeniority;
        }

        public String getSelectedTariff() {
            return selectedTariff;
        }

        public void setSelectedTariff(String selectedTariff) {
            this.selectedTariff = selectedTariff;
        }

        public String getSelectedCur() {
            return selectedCur;
        }

        public void setSelectedCur(String selectedCur) {
            this.selectedCur = selectedCur;
        }
        
 
        public List<Req> getReqList() {		
		return reqList;
	}
        
        public Req getSelectedReq() {
		return selectedReq;
	}
        
	public void setSelectedReq(Req selectedReq) {
		this.selectedReq = selectedReq;
	}

        public ReqG getSelectedReqG() {
            return selectedReqG;
        }

        public void setSelectedReqG(ReqG selectedReqG) {
            this.selectedReqG = selectedReqG;
        }
        
        
        // finding ProID for autosave in DB
        private void findProID(){            
            for(int i=0; i<proList.size(); i++){
                if (proList.get(i).getProName().equals(getSelectedPro())){
                    selectedReqG.setProID(proList.get(i).getProID());
                    break;
                }else{
                    // if other then rollback
                    selectedReqG.setProID(-1);
                }
            }
        }     
        
        // finding SrcID for autosave in DB
        private void findSrcID(){            
            for(int i=0; i<sourceList.size(); i++){
                if (sourceList.get(i).getSrcName().equals(getSelectedSrc())){
                    selectedReqG.setSrcID(sourceList.get(i).getSrcID());
                    break;
                }else{
                    // if other then rollback
                    selectedReqG.setSrcID(-1);
                }
            }
        }
        
        // finding OffID for autosave in DB
        private void findOffID(){            
            for(int i=0; i<offList.size(); i++){
                if (offList.get(i).getOffName().equals(getSelectedOff())){
                    selectedReqG.setOffID(offList.get(i).getOffID());
                    break;
                }else{
                    // if other then rollback
                    selectedReqG.setOffID(-1);
                }
            }
        }
        
        // finding RegID for autosave in DB
        private void findRegID(){            
            for(int i=0; i<regList.size(); i++){
                if (regList.get(i).getRegName().equals(getSelectedReg())){
                    selectedReqG.setRegID(regList.get(i).getRegID());
                    break;
                }else{
                    // if other then rollback
                    selectedReqG.setRegID(-1);
                }
            }
        }
        
        // finding WregID for autosave in DB
        private void findWregID(){            
            for(int i=0; i<regList.size(); i++){
                if (regList.get(i).getRegName().equals(getSelectedWReg())){
                    selectedReqG.setWregID(regList.get(i).getRegID());
                    break;
                }else{
                    // if other then rollback
                    selectedReqG.setWregID(-1);
                }
            }
        } 
        
        // findng TrfID for autosave in DB
        private void findTrfID(){            
            for(int i=0; i<trfList.size(); i++){
                if (trfList.get(i).getTrfName().equals(getSelectedTariff())){
                    selectedReqG.setTrfID(trfList.get(i).getTrfID());
                    break;
                }else{
                    // if other then rollback
                    selectedReqG.setTrfID("");
                }
            }
        }
        
        // findng CurID for autosave in DB
        private void findCurID(){            
            for(int i=0; i<curList.size(); i++){
                if (curList.get(i).getCurName().equals(getSelectedCur())){
                    selectedReqG.setCurID(curList.get(i).getCurID());
                    break;
                }else{
                    // if other then rollback
                    selectedReqG.setCurID("");
                }
            }
        }
        
        
        
        
        
        
        
        
        // finding PerName
        private String findPerName(String perID){
            String res = null;
            for (int i=0; i<perList.size(); i++){
                if (perList.get(i).getPerID().equals(perID)){
                    res = perList.get(i).getPerName();
                    break;
                }else{
                    res = null;
                }
            }
            return res;
        }       
                // finding StatusName
        private String findStatusName(String statusID){
            String res = null;
            for (int i=0; i<reqStatusList.size(); i++){
                if (reqStatusList.get(i).getReqStatusID().equals(statusID)){
                    res = reqStatusList.get(i).getReqStatusName();
                    break;
                }else{
                    res = null;
                }
            }
            return res;
        }
        
        // finding StatusID for autosave in DB
        private void findStatusID(){            
            for(int i=0; i<reqStatusList.size(); i++){
                if (reqStatusList.get(i).getReqStatusName().equals(selectedReq./*getStatusName*/getSource())){
                    selectedReq.setStatusID(reqStatusList.get(i).getReqStatusID());
                    break;
                }
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        // updating tRequest in DB
        public void updateSelectedReqG() {
        try {
            
            findProID();
            findSrcID();
            findOffID();
            findRegID();
            findWregID();
            findTrfID();
            findCurID();
            
            findStatusID();
            
            InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup("jdbc/__PreZayavkaDBPool");
				connTemp = ds.getConnection();
				stmtTemp = connTemp.prepareCall("{ call [dbo].[spRequest_iu](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                                stmtTemp.setInt(1, selectedReqG.getReqID());
                                stmtTemp.setInt(2, selectedReqG.getResID());
                                stmtTemp.setTimestamp(3, /*(java.sql.Timestamp)*/ selectedReqG.getCDate());                                          //!!!!!!!!!
				stmtTemp.setString(4, selectedReqG.getLName());
                                stmtTemp.setString(5, selectedReqG.getFName());
                                stmtTemp.setString(6, selectedReqG.getSName());
                                stmtTemp.setString(7, selectedReqG.getPhone());
                                stmtTemp.setString(8, selectedReqG.getEmaill());
                                stmtTemp.setString(9, selectedReqG.getRegion());
                                stmtTemp.setString(10, selectedReqG.getSource());
                                stmtTemp.setNull(11, 0);
                                if (selectedReqG.getRegID() == -1){
                                    stmtTemp.setNull(12, 0);    
                                }else{
                                    stmtTemp.setInt(12, selectedReqG.getRegID());
                                }
                                if (selectedReqG.getProID() == -1){
                                    stmtTemp.setNull(13, 0);    
                                }else{
                                    stmtTemp.setInt(13, selectedReqG.getProID());
                                }
                                if (selectedReqG.getSrcID() == -1){
                                    stmtTemp.setNull(14, 0);    
                                }else{
                                    stmtTemp.setInt(14, selectedReqG.getSrcID());
                                }
                                stmtTemp.setString(15, selectedReqG.getStatusID());
                                stmtTemp.setNull(16, 0);
                                stmtTemp.setNull(17, 0);
                                stmtTemp.setNull(18, 0);
                                stmtTemp.setNull(19, 0);
                                stmtTemp.setNull(20, 0);
                                if (selectedReqG.getTrfID()==null){
                                    stmtTemp.setNull(21, 0);    
                                }else{
                                    stmtTemp.setString(21, selectedReqG.getTrfID());
                                }
                                if (selectedReqG.getCurID()==null){
                                    stmtTemp.setNull(22, 0);    
                                }else{
                                    stmtTemp.setString(22, selectedReqG.getCurID());
                                }
                                stmtTemp.setFloat(23, selectedReqG.getVal());
                                stmtTemp.setInt(24, selectedReqG.getTerm());
                                if (selectedReqG.getOffID() == -1){
                                    stmtTemp.setNull(25, 0);    
                                }else{
                                    stmtTemp.setInt(25, selectedReqG.getOffID());
                                }
                                stmtTemp.setNull(26, 0);
                                stmtTemp.setNull(27, 0);
                                stmtTemp.setNull(28, 0);
                                stmtTemp.setNull(29, 0);
                                stmtTemp.setNull(30, 0);
                                stmtTemp.setNull(31, 0);
                                if (selectedReqG.getBDate()==null){
                                    stmtTemp.setNull(32, 0);
                                }else{
                                    stmtTemp.setTimestamp(32, new Timestamp(selectedReqG.getBDate().getTime()));
                                }
                                stmtTemp.setString(33, selectedReqG.isIsRes()?"Y":"N");
                                if (getSelectedConfirm()==null){
                                    stmtTemp.setNull(34, 0);
                                }else{
                                    stmtTemp.setString(34, getSelectedConfirm().equals("2НДФЛ")?"T":getSelectedConfirm().equals("Форма Банка")?"B":"B");
                                }
                                stmtTemp.setString(35, selectedReqG.getWregion());
                                if (selectedReqG.getWregID() == -1){
                                    stmtTemp.setNull(36, 0);    
                                }else{
                                    stmtTemp.setInt(36, selectedReqG.getWregID());
                                }
                                if (getSelectedTSeniority()==null){
                                    stmtTemp.setNull(37, 0);
                                }else{
                                    stmtTemp.setString(37, getSelectedTSeniority().equals("4 и более мес.")?"Y":getSelectedTSeniority().equals("менее 4 мес.")?"N":"N");
                                }
                                if (getSelectedCSeniority()==null){
                                    stmtTemp.setNull(38, 0);
                                }else{
                                    stmtTemp.setString(38, getSelectedCSeniority().equals("12 и более мес.")?"Y":getSelectedCSeniority().equals("менее 1 мес.")?"N":"N");
                                }
                                stmtTemp.setNull(39, 0);
                                stmtTemp.setNull(40, 0);
                                stmtTemp.setNull(41, 0);
                                stmtTemp.setNull(42, 0);
                                stmtTemp.setNull(43, 0);
                                stmtTemp.setNull(44, 0);
                                stmtTemp.setNull(45, 0);
                                stmtTemp.setNull(46, 0);
                                stmtTemp.setNull(47, 0);
                                stmtTemp.setNull(48, 0);
                                stmtTemp.setNull(49, 0);
                                
                                stmtTemp.setString(50, selectedReqG.getHphone());
                                stmtTemp.setString(51, selectedReqG.getWphone());
                                
                                stmtTemp.executeUpdate();
                                
        selectAddFieldForSelectedReqG();                        
        // for updating (clear on close)     
        updSelectedReq(); 
        //refresh();                          //08/10/2012 пока полный refresh, а надо бы возвращаться на редактируемую строку 
                                                                           
        } catch (SQLException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
 		stmtTemp.close(); 
 		connTemp.close();
            } catch (SQLException ex) {
                Logger.getLogger(TRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }

    public Filter getFilter() {
        return filter;
    }
    
    @Override
    public void processAction(ActionEvent ae) {
        refresh();
    }

    private void updSelectedReq() {
        selectedReq.setProID(selectedReqG.getProID());
        selectedReq.setProduct(findProNameTrim(getSelectedPro()));
        
        selectedReq.setFrmName(selectedReqG.getFrmName());
        
        selectedReq.setSrcID(selectedReqG.getSrcID());
        selectedReq.setSource(selectedReqG.getSource());
        
        selectedReq.setRegID(selectedReqG.getRegID());
        selectedReq.setRegion(selectedReqG.getRegion());
    }
        
    // finding ProNameTrim
    private String findProNameTrim(String proName){ 
      String proNameTrim = new String();
            for(int i=0; i<proList.size(); i++){
                if (proList.get(i).getProName().equals(proName)){
                    proNameTrim = proList.get(i).getProNameTrim();
                    break;
                }
            }
            return proNameTrim;
    }

}
