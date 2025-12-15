package com.Inwarrenty.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Inwarrenty.Utils.CreateJobBeanMapper;
import com.Inwarrenty.database.dao.CreateJobPayloadDataDao;
import com.Inwarrenty.dataproviderbean.CreateJobBean;
import com.Inwarrenty.request.model.CreateJobAPIPayload;

public class DemoJDBC {
	public static void main(String[] args) throws SQLException {
		
//		DataBaseManager.CreateConnection();
//		DataBaseManager.CreateConnection();
//		DataBaseManager.CreateConnection();
//		DataBaseManager.CreateConnection();
//		 Connection conn=  DataBaseManagerWithHikariCP.getConnecction();
//		System.out.println(conn);
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
		
	List<CreateJobBean> beanlist=	CreateJobPayloadDataDao.getCreatePayloadData();
	List<CreateJobAPIPayload>payloadlist = new ArrayList<CreateJobAPIPayload>();
	for(CreateJobBean createjob :beanlist) {
		CreateJobAPIPayload payload = CreateJobBeanMapper.mapper(createjob);
		payloadlist.add(payload);
		
	}
	for(CreateJobAPIPayload payload :payloadlist) {
		System.out.println(payload);
	}
		
		
	}

}
