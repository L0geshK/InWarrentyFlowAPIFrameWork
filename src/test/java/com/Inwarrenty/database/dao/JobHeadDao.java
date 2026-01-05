package com.Inwarrenty.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import com.Inwarrenty.database.DataBaseManagerWithHikariCP;
import com.Inwarrenty.db.model.JobHeadDBModel;

public class JobHeadDao {
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(JobHeadDao.class);
	private static final String JOB_HEAD_QUERY="""
			SELECT * FROM tr_job_head where tr_customer_id=?;
			
			""";

	
	private JobHeadDao() {
		
	}
	
	public static JobHeadDBModel getJobHeadDetails(int tr_customer_id) {
		log.info("Entry");
		Connection conn=null;
		 ResultSet result= null; 
		 PreparedStatement statement=null;
		 JobHeadDBModel jobHeadDBModel = null;
		 try {
			conn = DataBaseManagerWithHikariCP.getConnecction();
			statement=conn.prepareStatement(JOB_HEAD_QUERY);
			statement.setInt(1, tr_customer_id);
			result = statement.executeQuery();
			 while(result.next()) {
				 
				 jobHeadDBModel =  new JobHeadDBModel(result.getInt("id"), result.getString("job_number"), result.getInt("tr_customer_id"), result.getInt("tr_customer_product_id"), result.getInt("mst_service_location_id"), result.getInt("mst_platform_id"), result.getInt("mst_warrenty_status_id"), result.getInt("mst_oem_id"));
				 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 log.info("Exit");
		 return jobHeadDBModel;
		
	}
		
	
}
