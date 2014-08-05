package com.transcapital.osok.webserv;


import java.sql.*;
import java.util.ArrayList;

public class ConnectToDB {
	
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

	public ConnectToDB(Zayavka z, String conn) throws ClassNotFoundException, SQLException {

		try {
			
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                if ((conn == null) || (conn.equals(""))) {
                    connTemp = DriverManager.getConnection("jdbc:sqlserver://mars\\v2008;databasename=tcallcentr;user=test-callc;password=23180945");
                } else {
                    connTemp = DriverManager.getConnection(conn); 
                }
                //werwerdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
                //werwerdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
                
                stmtTemp = connTemp.prepareCall("{ call [dbo].[spRequest_iu](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                                stmtTemp.setNull(1, 0);
                                stmtTemp.setNull(2, 0);
                                stmtTemp.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				stmtTemp.setString(4, z.getFamily()); //stmtTemp.setString(4, selectedReqG.getLName());
                                stmtTemp.setString(5, z.getName()); //stmtTemp.setString(5, selectedReqG.getFName());
                                stmtTemp.setString(6, z.getPatr()); //stmtTemp.setString(6, selectedReqG.getSName());
                                stmtTemp.setString(7, z.getPhone()); //stmtTemp.setString(7, selectedReqG.getPhone());
                                stmtTemp.setString(8, z.getEmail()); //stmtTemp.setString(8, selectedReqG.getEmaill());                               
                                stmtTemp.setNull(9, 0);
                                stmtTemp.setNull(10, 0);
                                stmtTemp.setNull(11, 0);
                                stmtTemp.setNull(12, 0);
                                stmtTemp.setNull(13, 0);
                                stmtTemp.setNull(14, 0);
                                stmtTemp.setNull(15, 0);
                                stmtTemp.setNull(16, 0);
                                stmtTemp.setNull(17, 0);
                                stmtTemp.setNull(18, 0);
                                stmtTemp.setNull(19, 0);
                                stmtTemp.setNull(20, 0);
                                stmtTemp.setNull(21, 0);
                                stmtTemp.setNull(22, 0);
                                stmtTemp.setNull(23, 0);
                                stmtTemp.setNull(24, 0);
                                stmtTemp.setNull(25, 0);
                                stmtTemp.setNull(26, 0);
                                stmtTemp.setInt(27, 5);                         // !!! забить новый реальный тип заявки (???)
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
