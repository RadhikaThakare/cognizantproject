package com.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DButil {
	
	    public static Connection provideConnection(){
	      
	    	  Connection con=null;
	    	  
	    	try {
				String url="jdbc:mysql://localhost:3306/onlinebankingsystem";
				String userName="root";
				String password="root";

			//Connection build
				con=DriverManager.getConnection(url, userName, password);
				
				
			}catch(SQLException e){
				e.printStackTrace();
			}
	    	
	    	return con;
		}


}
