package com.Inwarrenty.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Inwarrenty.database.DataBaseManagerWithHikariCP;
import com.Inwarrenty.dataproviderbean.CreateJobBean;

public class CreateJobPayloadDataDao {
	private static String SqlQuery = """
						SELECT
			mst_service_location_id,
			mst_platform_id,
			mst_warrenty_status_id,
			mst_oem_id,
			    first_name,
			    last_name,
			    mobile_number,
			    mobile_number_alt,
			    email_id,
			    email_id_alt,
			    flat_number,
			    apartment_name,
			    street_name,
			    landmark,
			    area,
			    pincode,
			    country,
			    state,
			    serial_number,
			    imei1,
			    imei2,
			    popurl,
			    dop,
			    mst_model_id,
			    mst_problem_id,
			remark
			FROM tr_customer
			INNER JOIN tr_customer_address
			    ON tr_customer.id = tr_customer_address.id
			INNER JOIN tr_customer_product
			    ON tr_customer_product.tr_customer_id = tr_customer.id
			INNER JOIN tr_job_head
			    ON tr_job_head.tr_customer_id = tr_customer.id
			INNER JOIN map_job_problem
			    ON tr_job_head.tr_customer_id = map_job_problem.tr_job_head_id
			LIMIT 5;

						""";
	
	public static List<CreateJobBean> getCreatePayloadData() {
		Connection conn=null;
		Statement statement=null;
		 ResultSet result= null;
		 CreateJobBean bean = new CreateJobBean();
		 List<CreateJobBean> beanlist = new ArrayList<CreateJobBean>();
	 try {
		 conn=	DataBaseManagerWithHikariCP.getConnecction();
		 statement= conn.createStatement();
		 result= statement.executeQuery(SqlQuery);
		 while(result.next()) {
			 
			
			bean.setMst_service_location_id(result.getString("mst_service_location_id"));
			bean.setMst_platform_id(result.getString("mst_platform_id"));
			bean.setMst_warrenty_status_id(result.getString("mst_warrenty_status_id"));
			bean.setMst_oem_id(result.getString("mst_oem_id"));
			 bean.setCustomer__first_name(result.getString("first_name"));
			 bean.setCustomer__last_name(result.getString("last_name"));
			 bean.setCustomer__mobile_number(result.getString("mobile_number"));
			 bean.setCustomer__mobile_number_alt(result.getString("mobile_number_alt"));
			 bean.setCustomer__email_id(result.getString("email_id"));
			 bean.setCustomer__email_id_alt(result.getString("email_id_alt"));
			 bean.setCustomer_address__flat_number(result.getString("flat_number"));
			 bean.setCustomer_address__apartment_name(result.getString("apartment_name"));
			 bean.setCustomer_address__street_name(result.getString("street_name"));
			 bean.setCustomer_address__landmark(result.getString("landmark"));
			 bean.setCustomer_address__area(result.getString("area"));
			 bean.setCustomer_address__pincode(result.getString("pincode"));
			 bean.setCustomer_address__country(result.getString("country"));
			 bean.setCustomer_address__state(result.getString("state"));
			 bean.setCustomer_product__serial_number(result.getString("serial_number"));
			 bean.setCustomer_product__imei1(result.getString("imei1"));
			 bean.setCustomer_product__imei2(result.getString("imei2"));
			 bean.setCustomer_product__popurl(result.getString("popurl"));
			 bean.setCustomer_product__dop(result.getString("dop"));
			 bean.setCustomer_product__mst_model_id("1");

			 bean.setProblems__id(result.getString("mst_problem_id"));
			 bean.setProblems__remark(result.getString("remark"));
			 bean.setCustomer_product__product_id("1");
			 beanlist.add(bean);
		 }
	 } catch (SQLException e) {
		// TODO Auto-generated catch block	
		e.printStackTrace();
	 }
	 System.out.println(bean);
	 for(CreateJobBean b :beanlist) {
		 System.out.println(b);
	 }
	 return beanlist;
	
	}

}
