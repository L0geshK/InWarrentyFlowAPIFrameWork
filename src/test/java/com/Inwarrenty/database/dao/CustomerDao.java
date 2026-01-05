package com.Inwarrenty.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import com.Inwarrenty.Utils.JsonReaderUtil;
import com.Inwarrenty.database.DataBaseManagerWithHikariCP;
import com.Inwarrenty.db.model.CustomerDBModel;

public class CustomerDao {
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(CustomerDao.class);
	private static final String CUSTOMER_DETAILS_QUERY = 
			"""
			 SELECT * FROM tr_customer where id = ?  
			""";
			
	
	
	public static CustomerDBModel getCustomerinfo(int customerId) {
		log.info("Entry");
		Connection conn=null;
		 ResultSet result= null; 
		 PreparedStatement statement=null;
		 CustomerDBModel customermodel = null;
		 try {
			conn = DataBaseManagerWithHikariCP.getConnecction();
			statement=conn.prepareStatement(CUSTOMER_DETAILS_QUERY);
			statement.setInt(1, customerId);
			result = statement.executeQuery();
			 while(result.next()) {
				 System.out.println(result.getString("first_name"));
				 customermodel = new CustomerDBModel(result.getInt("id"),result.getString("first_name"),result.getString("last_name"),result.getString("mobile_number"),result.getString("mobile_number_alt"),result.getString("email_id"),result.getString("email_id_alt"),result.getInt("tr_customer_address_id"));
				 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 log.info("Exit");
		 return customermodel;
		 
	}
	

}
