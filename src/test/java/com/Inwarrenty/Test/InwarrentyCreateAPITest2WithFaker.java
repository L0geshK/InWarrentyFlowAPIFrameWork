package com.Inwarrenty.Test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.FakerDataGenerator;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.database.dao.CustomerAddressDao;
import com.Inwarrenty.database.dao.CustomerDao;
import com.Inwarrenty.database.dao.CustomerProductDao;
import com.Inwarrenty.database.dao.JobHeadDao;
import com.Inwarrenty.database.dao.MapJobProblemDao;
import com.Inwarrenty.db.model.CustomerAddressDBModel;
import com.Inwarrenty.db.model.CustomerDBModel;
import com.Inwarrenty.db.model.CustomerProductDBModel;
import com.Inwarrenty.db.model.JobHeadDBModel;
import com.Inwarrenty.db.model.MapJobProblemDBModel;
import com.Inwarrenty.request.model.CreateJobAPIPayload;
import com.Inwarrenty.request.model.Customer;
import com.Inwarrenty.request.model.CustomerAddress;
import com.Inwarrenty.request.model.CustomerProduct;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class InwarrentyCreateAPITest2WithFaker {
	 private CreateJobAPIPayload createJobAPIPayload;
	 
	
	 
	 
	@BeforeMethod(description = "SetUp the Payload for Create API Request:")
	public void setup() {

		createJobAPIPayload = FakerDataGenerator.generateFakeCreateJobData();
	}
	
	
	@Test(description = "Verifying if the  Create api is able to Create InWarrenty Job ",groups = {"api","regression","smoke"})
	public void createJobApi() throws IOException {
		
		
		
		Response response=given() 
		
		.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, createJobAPIPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/CreateAPIResponceSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data", Matchers.notNullValue())
		.body("data.job_number", Matchers.startsWith("JOB_"))
		.extract().response();
		//.extract().body().jsonPath().getInt("data.tr_customer_id"); --> this method for extrat one data 
		
		int customerid =response.then().extract().jsonPath().getInt("data.tr_customer_id");
		
		
		Customer expecteddata = createJobAPIPayload.customer();
		CustomerAddress expecteAddddata = createJobAPIPayload.customer_address();
		CustomerProduct expectedProdata = createJobAPIPayload.customer_product();
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
		
		int productID = response.then().extract().jsonPath().getInt("data.tr_customer_product_id");
		CustomerProductDBModel customerProddata = CustomerProductDao.getproductInfoFromDB(productID);
		Assert.assertEquals(expectedProdata.imei1(), customerProddata.getImei1());
		Assert.assertEquals(expectedProdata.imei2(), customerProddata.getImei2());
		Assert.assertEquals(expectedProdata.serial_number(), customerProddata.getSerial_number());
		Assert.assertEquals(expectedProdata.popurl(), customerProddata.getPopurl());
		//Assert.assertEquals(expectedProdata.dop(), customerProddata.getDop());
		Assert.assertEquals(expectedProdata.mst_model_id(), customerProddata.getMst_model_id());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(expectedProdata.dop(), customerProddata.getDop());
		
		
		int tr_job_head_id = response.then().extract().jsonPath().getInt("data.id");
		MapJobProblemDBModel mapjobproblem = MapJobProblemDao.getProblemDetails(tr_job_head_id);
		
		Assert.assertEquals(mapjobproblem.getMst_problem_id(), createJobAPIPayload.problems().get(0).id());
		Assert.assertEquals(mapjobproblem.getRemark(), createJobAPIPayload.problems().get(0).remark());
		int trobhead_id = response.then().extract().jsonPath().getInt("data.id");
		JobHeadDBModel jobheadbmodel = JobHeadDao.getJobHeadDetails(trobhead_id);
		Assert.assertEquals(jobheadbmodel.getMst_oem_id(), createJobAPIPayload.mst_oem_id());
		


		
		
		
	}

}
