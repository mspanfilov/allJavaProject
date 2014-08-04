package com.transcapital.dit.osok.cpp;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectToOracleDB {
	
	private CallableStatement stmtTemp;
	private Connection connTemp;
	
	public String test;
	private ArrayList<String> columnName;
	private ArrayList<ArrayList<String>> rows;

	public ArrayList<String> getColumnName() {
		return columnName;
	}
	public ArrayList<ArrayList<String>> getRows() {
		return rows;
	}

	public ConnectToOracleDB(/*Zayavka z, Vzayavka vz, */String conn) throws ClassNotFoundException, SQLException {

		try {
			
		Class.forName("oracle.jdbc.OracleDriver");

                if ((conn == null) || (conn.equals(""))) {
                    connTemp = DriverManager.getConnection("jdbc:oracle:thin:@10.99.200.47:1521:mbank","cyberplat2","cyberplat2");
                } else {
                    connTemp = DriverManager.getConnection(conn); 
                }
                
                /*stmtTemp = connTemp.prepareCall("INSERT INTO zayavka(family, name, patr, birthday, phone, email, summ, period, nationality, region_of_residence, region_of_employment, form, seniority, seniority_all, traffic_source, status, datedownload, ext_id, dateunload, vsumm, vperiod, vbirthday, vregion1, vregion2, vform, vtraffic_source, product, vproduct, existsfixphone, work, vwork, formwork, vformwork, form2, vform2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmtTemp.setString(1, z.getFamily());
                                stmtTemp.setString(2, z.getName());
                                stmtTemp.setString(3, z.getPatr());
                                stmtTemp.setString(4, z.getBirthday());
                                stmtTemp.setString(5, z.getPhone());
                                stmtTemp.setString(6, z.getEmail()); 
                                stmtTemp.setString(7, z.getSumm());
                                stmtTemp.setString(8, z.getPeriod());
                                stmtTemp.setString(9, z.getNationality());
                                stmtTemp.setString(10, z.getRor());
                                stmtTemp.setString(11, z.getRoe());
                                stmtTemp.setString(12, z.getForm());
                                
                                stmtTemp.setString(13, z.getSeniority());
                                stmtTemp.setString(14, z.getSeniority_all());
                                stmtTemp.setString(15, z.getTraffic_source());
                         
                                stmtTemp.setNull(17, 0);
                                stmtTemp.setNull(18, 0);
                                stmtTemp.setNull(19, 0);
                                
                                stmtTemp.setString(27, z.getProduct());
                                
                                stmtTemp.setString(29, z.getExistsfixphone());
                                stmtTemp.setString(30, z.getWork());
                                stmtTemp.setString(32, z.getFormwork());
                                stmtTemp.setString(34, z.getForm2());
                                
                                if (vz != null) {
                                    stmtTemp.setInt(16, 1);
                                    
                                    stmtTemp.setDouble(20, vz.getVsumm());
                                    stmtTemp.setShort(21, vz.getVperiod());
                                    stmtTemp.setDate(22, vz.getVbirthday());
                                    stmtTemp.setInt(23, vz.getVregion1());
                                    stmtTemp.setInt(24, vz.getVregion2());
                                    if (vz.getVform() != null) {stmtTemp.setInt(25, vz.getVform());} else {stmtTemp.setNull(25, 0);};
                                    stmtTemp.setString(26, vz.getVtraffic_source());
                                    stmtTemp.setInt(28, vz.getVproduct());
                                    if (vz.getVwork() != null) {stmtTemp.setInt(31, vz.getVwork());} else {stmtTemp.setNull(31, 0);};
                                    if (vz.getVformwork() != null) {stmtTemp.setInt(33, vz.getVformwork());} else {stmtTemp.setNull(33, 0);};
                                    if (vz.getVform2() != null) {stmtTemp.setInt(35, vz.getVform2());} else {stmtTemp.setNull(35, 0);};
                                }
                                else {
                                    stmtTemp.setInt(16, 0);
                                    
                                    stmtTemp.setNull(20, 0);
                                    stmtTemp.setNull(21, 0);
                                    stmtTemp.setNull(22, 0);
                                    stmtTemp.setNull(23, 0);
                                    stmtTemp.setNull(24, 0);
                                    stmtTemp.setNull(25, 0);
                                    stmtTemp.setNull(26, 0);
                                    stmtTemp.setNull(28, 0);
                                    stmtTemp.setNull(31, 0);
                                    stmtTemp.setNull(33, 0);
                                    stmtTemp.setNull(35, 0);
                                }
                                
                                stmtTemp.executeUpdate();*/
		
		} finally{
 	       // clean up the system resources
 	       try{
 	    	    

 		   //stmtTemp.close(); 

 		   connTemp.close();

 	       } catch(Exception e){
 	           e.printStackTrace();
 	       } 
 	   }
	}

}
