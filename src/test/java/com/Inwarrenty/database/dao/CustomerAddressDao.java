package com.Inwarrenty.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import com.Inwarrenty.Utils.JsonReaderUtil;
import com.Inwarrenty.database.DataBaseManagerWithHikariCP;
import com.Inwarrenty.db.model.CustomerAddressDBModel;

public class CustomerAddressDao {
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(CustomerAddressDao.class);
	private static final String CUSTOMER_ADD_QUERY = """
									select id,
			flat_number,
			apartment_name,
			street_name,
			landmark,
			area,
			pincode,
			country,
			state
			 from tr_customer_address where id=?

									""";

	private CustomerAddressDao() {

	}

	public static CustomerAddressDBModel getCustomerAddressData(int customerAddressId) {
		log.info("Entry");
		Connection conn = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		CustomerAddressDBModel customerAddressmodel = null;
		try {
			conn = DataBaseManagerWithHikariCP.getConnecction();
			statement = conn.prepareStatement(CUSTOMER_ADD_QUERY);
			statement.setInt(1, customerAddressId);
			;
			result = statement.executeQuery();
			while (result.next()) {
				
				customerAddressmodel = new CustomerAddressDBModel(result.getInt("id"), result.getString("flat_number"),
						result.getString("apartment_name"), result.getString("street_name"),
						result.getString("landmark"), result.getString("area"), result.getString("pincode"),
						result.getString("country"), result.getString("state"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Exit");
		return customerAddressmodel;
	}

}
