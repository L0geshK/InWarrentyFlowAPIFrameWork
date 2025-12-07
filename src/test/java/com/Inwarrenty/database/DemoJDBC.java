package com.Inwarrenty.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoJDBC {
	public static void main(String[] args) throws SQLException {
		
//		DataBaseManager.CreateConnection();
//		DataBaseManager.CreateConnection();
//		DataBaseManager.CreateConnection();
		DataBaseManager.CreateConnection();
		 Connection conn=  DataBaseManagerWithHikariCP.getConnecction();
		System.out.println(conn);
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:mysql://64.227.160.186:3306/SR_DEV", "srdev_ro_automation", "Srdev@123");
//			java.sql.Statement statement = conn.createStatement();
//		    ResultSet result= statement.executeQuery("select first_name, last_name ,mobile_number from tr_customer;");
//		    while(result.next()) {
//		    	String firstname = result.getString("first_name");
//		    	String lastname = result.getString("last_name");
//		    	String mobileno = result.getString("mobile_number");
//		    	System.out.println(firstname +"| "+lastname +" | "+mobileno);
//		    }
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
	}

}
