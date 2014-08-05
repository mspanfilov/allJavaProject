package com.transcapital.osok.webserv;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectToMySQLDB {
	
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

	public ConnectToMySQLDB(Zayavka z, Vzayavka vz, String conn) throws ClassNotFoundException, SQLException {

		try {
			
		Class.forName("com.mysql.jdbc.Driver");

                if ((conn == null) || (conn.equals(""))) {
                    //connTemp = DriverManager.getConnection("jdbc:mysql://10.97.200.42:3306/transit?user=osok&password=osok");
                    connTemp = DriverManager.getConnection("jdbc:mysql://10.99.202.13:3306/transit","osok","osok");
                } else {
                    connTemp = DriverManager.getConnection(conn); 
                }
                
                //Ошибка SQL! Количество добавленных заявок: 0 Message: Access denied for user 'osok'@'v-gabriella' (using password: YES)
                
                stmtTemp = connTemp.prepareCall("INSERT INTO transit.zayavka(classified, family, name, patr, birthday, phone, email, summ, period, nationality, region_of_residence, region_of_employment, form, seniority, seniority_all, status, datedownload, ext_id, dateunload, vsumm, vperiod, vbirthday, vregion1, vregion2, vform) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                stmtTemp.setNull(1, 0);
				stmtTemp.setString(2, z.getFamily());
                                stmtTemp.setString(3, z.getName());
                                stmtTemp.setString(4, z.getPatr());
                                stmtTemp.setString(5, z.getBirthday());
                                stmtTemp.setString(6, z.getPhone());
                                stmtTemp.setString(7, z.getEmail()); 
                                stmtTemp.setString(8, z.getSumm());
                                stmtTemp.setString(9, z.getPeriod());
                                stmtTemp.setString(10, z.getNationality());
                                stmtTemp.setString(11, z.getRor());
                                stmtTemp.setString(12, z.getRoe());
                                stmtTemp.setString(13, z.getForm());
                                
                                stmtTemp.setString(14, z.getSeniority());
                                stmtTemp.setString(15, z.getSeniority_all());
                         
                                stmtTemp.setNull(17, 0);
                                stmtTemp.setNull(18, 0);
                                stmtTemp.setNull(19, 0);
                                
                                if (vz != null) {
                                    stmtTemp.setInt(16, 1);
                                    
                                    stmtTemp.setDouble(20, vz.getVsumm());
                                    stmtTemp.setShort(21, vz.getVperiod());
                                    stmtTemp.setDate(22, vz.getVbirthday());
                                    stmtTemp.setInt(23, vz.getVregion1());
                                    stmtTemp.setInt(24, vz.getVregion2());
                                    if (vz.getVform() != null) {stmtTemp.setInt(25, vz.getVform());} else {stmtTemp.setNull(25, 0);};
                                }
                                else {
                                    stmtTemp.setInt(16, 0);
                                    
                                    stmtTemp.setNull(20, 0);
                                    stmtTemp.setNull(21, 0);
                                    stmtTemp.setNull(22, 0);
                                    stmtTemp.setNull(23, 0);
                                    stmtTemp.setNull(24, 0);
                                    stmtTemp.setNull(25, 0);
                                }
                                
                                stmtTemp.executeUpdate();
		
		} finally{
 	       // clean up the system resources
 	       try{
 	    	    

 		   stmtTemp.close(); 

 		   connTemp.close();

 	       } catch(Exception e){
 	           e.printStackTrace();
 	       } 
 	   }
	}

}
