package com.Inwarrenty.dataprovidertest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Models;
import com.Inwarrenty.Constants.OemId;
import com.Inwarrenty.Constants.Platform;
import com.Inwarrenty.Constants.Problem;
import com.Inwarrenty.Constants.Products;
import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Constants.ServiceLocation;
import com.Inwarrenty.Constants.WarrentyStatus;
import com.Inwarrenty.Utils.DateTimeUtils;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.database.dao.CustomerAddressDao;
import com.Inwarrenty.database.dao.CustomerDao;
import com.Inwarrenty.db.model.CustomerAddressDBModel;
import com.Inwarrenty.db.model.CustomerDBModel;
import com.Inwarrenty.request.model.CreateJobAPIPayload;
import com.Inwarrenty.request.model.Customer;
import com.Inwarrenty.request.model.CustomerAddress;
import com.Inwarrenty.request.model.CustomerProduct;
import com.Inwarrenty.request.model.Problems;

import io.restassured.module.jsv.JsonSchemaValidator;

public class InwarrentyCreateAPITestDBDataProvider {
	 private CreateJobAPIPayload createJobAPIPayload;
	 private  Customer Customer;
	 CustomerAddress CustomerAddress;
	
	 
	 
	@BeforeMethod(description = "SetUp the Payload for Create API Request:")
	public void setup() {

		Customer = new Customer("Bula11", "Peffeeer", "296-360-0729", "", "Christoillms79@gmail.com", "");
		CustomerAddress CustomerAddress = new CustomerAddress("c 304", "Jupiter", "MG road	", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
		CustomerProduct CustomerProduct = new CustomerProduct(DateTimeUtils.getTimeWithDaysAgo(10), "10558685491190", "10558685491190", "10558685491190", DateTimeUtils.getTimeWithDaysAgo(10), Products.NEXUS_2.getcode(), Models.NEXUS_BLUE.getmodelcode());
		Problems problems = new Problems(Problem.BATTERY_ISSUE.getproblemcode(), "Battery Issue");
		List<Problems> problemArray = new ArrayList<Problems>();
		problemArray.add(problems);
		
		
		 createJobAPIPayload = new CreateJobAPIPayload(ServiceLocation.SERVICE_lOCATION_A.getlocationcode(), Platform.FRONT_DESK.getplatformcode(), WarrentyStatus.IN_WARRENTY.getwarrentcode(),OemId.GOOGLE.getomeidcode() , Customer, CustomerAddress, CustomerProduct, problemArray);
		
		
		
	}
	
	
	@Test(description = "Verifying if the  Create api is able to Create InWarrenty Job ",groups = {"api","regression","smoke"})
	public void createJobApi() throws IOException {
		
		
		
	int customerID =	given() 
		
		.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, createJobAPIPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/CreateAPIResponceSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data", Matchers.notNullValue())
		.body("data.job_number", Matchers.startsWith("JOB_"))
		.extract().body().jsonPath().getInt("data.tr_customer_id");
		
		
		CustomerDBModel customermodel =CustomerDao.getCustomerinfo(customerID);
		CustomerAddressDBModel customerAddmodel=CustomerAddressDao.getCustomerAddressData(customermodel.getTr_customer_address_id());
		
		
		Assert.assertEquals(Customer.first_name(), customermodel.getFirst_name());
		Assert.assertEquals(Customer.last_name(), customermodel.getLast_name());
		Assert.assertEquals(Customer.mobile_number(), customermodel.getMobile_number());
		Assert.assertEquals(Customer.mobile_number_alt(), customermodel.getMobile_number_alt());
		Assert.assertEquals(Customer.email_id(), customermodel.getMobile_number());
		Assert.assertEquals(Customer.email_id_alt(), customermodel.getMobile_number_alt());
		
		
		Assert.assertEquals(CustomerAddress.flat_number(), customerAddmodel.getFlat_number());
		Assert.assertEquals(CustomerAddress.apartment_name(), customerAddmodel.getApartment_name());
		Assert.assertEquals(CustomerAddress.street_name(),customerAddmodel.getStreet_name());
		Assert.assertEquals(CustomerAddress.landmark(), customerAddmodel.getLandmark());
		Assert.assertEquals(CustomerAddress.area(), customerAddmodel.getArea());
		Assert.assertEquals(CustomerAddress.pincode(),customerAddmodel.getPincode());
		Assert.assertEquals(CustomerAddress.country(), customerAddmodel.getCountry());
		Assert.assertEquals(CustomerAddress.state(), customerAddmodel.getState());
		
		
		
		
		
	}

}
