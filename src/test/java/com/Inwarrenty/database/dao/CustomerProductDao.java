package com.Inwarrenty.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import com.Inwarrenty.database.DataBaseManagerWithHikariCP;
import com.Inwarrenty.db.model.CustomerProductDBModel;

import io.qameta.allure.Step;

public class CustomerProductDao {
	
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(CustomerProductDao.class);
	
	private static final String PRODUCT_QUERY ="""
			select * from tr_customer_product  where id =?;
			
			""";

	
	private CustomerProductDao() {
		
	}
	
	@Step("Reterving Data from DB for CustomerProductDBModel")
	public static CustomerProductDBModel getproductInfoFromDB(int tr_customer_product_id) {
		log.info("Entry");
		Connection conn = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		CustomerProductDBModel customerProductDBModel = null;
		try {
			conn = DataBaseManagerWithHikariCP.getConnecction();
			statement = conn.prepareStatement(PRODUCT_QUERY);
			statement.setInt(1, tr_customer_product_id);
			;
			result = statement.executeQuery();
			while (result.next()) {
				
				customerProductDBModel = new CustomerProductDBModel(result.getInt("id"), result.getInt("tr_customer_id"), result.getInt("mst_model_id"), result.getString("dop"), result.getString("popurl"), result.getString("imei2"), result.getString("imei1"), result.getString("serial_number"));

			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		log.info("Exit");
		return customerProductDBModel;
	}
		
	

	
}
