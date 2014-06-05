package testmultipleconnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToOracleDB {
	
	//private CallableStatement stmtTemp;
	public Connection connTemp;

	public ConnectToOracleDB() throws ClassNotFoundException, SQLException {
            
            String conn = null;

		try {
			
		Class.forName("oracle.jdbc.OracleDriver");

                if ((conn == null) || (conn.equals(""))) {
                    connTemp = DriverManager.getConnection("-----------------");
                } else {
                    connTemp = DriverManager.getConnection(conn); 
                }
                
                System.out.println(" connection open ");
		
		} finally{
 	       // clean up the system resources
 	       try{
 	    	    

 		   //stmtTemp.close(); 

 		   //connTemp.close();

 	       } catch(Exception e){
 	           e.printStackTrace();
 	       } 
 	   }
	}
        
        public void CloseConnectToOracleDB() throws ClassNotFoundException, SQLException {
            connTemp.close();
            System.out.println(" connection close ");
        }

}
