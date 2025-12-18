package com.Inwarrenty.Test;

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
import com.Inwarrenty.Utils.FakerDataGenerator;
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

public class InwarrentyCreateAPITest2WithFaker {
	 private CreateJobAPIPayload createJobAPIPayload;
	
	 
	 
	@BeforeMethod(description = "SetUp the Payload for Create API Request:")
	public void setup() {

		createJobAPIPayload = FakerDataGenerator.generateFakeCreateJobData();
	}
	
	
	@Test(description = "Verifying if the  Create api is able to Create InWarrenty Job ",groups = {"api","regression","smoke"})
	public void createJobApi() throws IOException {
		
		
		
		int customerid=given() 
		
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
		Customer expecteddata = createJobAPIPayload.customer();
		CustomerAddress expecteAddddata = createJobAPIPayload.customer_address();
		CustomerDBModel customermodel=CustomerDao.getCustomerinfo(customerid);
      CustomerAddressDBModel customerAddmodel=CustomerAddressDao.getCustomerAddressData(customermodel.getTr_customer_address_id());
		
		
		Assert.assertEquals(expecteddata.first_name(), customermodel.getFirst_name());
		Assert.assertEquals(expecteddata.last_name(), customermodel.getLast_name());
		Assert.assertEquals(expecteddata.mobile_number(), customermodel.getMobile_number());
		Assert.assertEquals(expecteddata.mobile_number_alt(), customermodel.getMobile_number_alt());
		Assert.assertEquals(expecteddata.email_id(), customermodel.getEmail_id());
		Assert.assertEquals(expecteddata.email_id_alt(), customermodel.getEmail_id_alt());
		
		
		Assert.assertEquals(expecteAddddata.flat_number(), customerAddmodel.getFlat_number());
		Assert.assertEquals(expecteAddddata.apartment_name(), customerAddmodel.getApartment_name());
		Assert.assertEquals(expecteAddddata.street_name(),customerAddmodel.getStreet_name());
		Assert.assertEquals(expecteAddddata.landmark(), customerAddmodel.getLandmark());
		Assert.assertEquals(expecteAddddata.area(), customerAddmodel.getArea());
		Assert.assertEquals(expecteAddddata.pincode(),customerAddmodel.getPincode());
		Assert.assertEquals(expecteAddddata.country(), customerAddmodel.getCountry());
		Assert.assertEquals(expecteAddddata.state(), customerAddmodel.getState());
		
		
		
		
	}

}
