package com.Inwarrenty.Utils;

import java.util.ArrayList;
import java.util.List;

import com.Inwarrenty.dataproviderbean.CreateJobBean;
import com.Inwarrenty.request.model.CreateJobAPIPayload;
import com.Inwarrenty.request.model.Customer;
import com.Inwarrenty.request.model.CustomerAddress;
import com.Inwarrenty.request.model.CustomerProduct;
import com.Inwarrenty.request.model.problems;

public class CreateJobBeanMapper {
	
	private CreateJobBeanMapper() {
		
	}
	
	public static CreateJobAPIPayload mapper(CreateJobBean bean)
	{
		int mstservicelocationid = Integer.parseInt(bean.getMst_service_location_id());
		int mstplatformid = Integer.parseInt(bean.getMst_platform_id());
		int mstwarrentystatusid = Integer.parseInt(bean.getMst_warrenty_status_id());
		int Mstoemid = Integer.parseInt(bean.getMst_oem_id());
		
		Customer customer =  new Customer(
				bean.getCustomer__first_name(),
				bean.getCustomer__last_name(),
				bean.getCustomer__mobile_number(),
				bean.getCustomer__mobile_number_alt(),
				bean.getCustomer__email_id(),
				bean.getCustomer__email_id_alt());
		
		CustomerAddress customerAddress = new CustomerAddress(bean.getCustomer_address__flat_number(),
				bean.getCustomer_address__apartment_name(),
				bean.getCustomer_address__street_name(),
				bean.getCustomer_address__landmark(),
				bean.getCustomer_address__area(),
				bean.getCustomer_address__pincode(),
				bean.getCustomer_address__state(),
				bean.getCustomer_address__country());
		
		int mstwarrentystatusId = Integer.parseInt(bean.getMst_warrenty_status_id());
		int MstoemId = Integer.parseInt(bean.getMst_oem_id());
		
		CustomerProduct customerproduct = new CustomerProduct(bean.getCustomer_product__dop(),
				bean.getCustomer_product__serial_number(), 
				bean.getCustomer_product__imei1(),
				bean.getCustomer_product__imei2(), 
				bean.getCustomer_product__popurl(),
				mstwarrentystatusId, MstoemId);
		
		List<problems>problemlist = new ArrayList<problems>();
		int prodbleid = Integer.parseInt(bean.getProblems__id());
		
		problems problem = new problems(prodbleid,bean.getProblems__remark());
		problemlist.add(problem);
		
		CreateJobAPIPayload payload = new CreateJobAPIPayload(mstservicelocationid, mstplatformid, mstwarrentystatusid, Mstoemid, customer, customerAddress, customerproduct, problemlist);
		return payload;
	}
}
