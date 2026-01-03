package com.Inwarrenty.Test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
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
import com.Inwarrenty.request.model.CreateJobAPIPayload;
import com.Inwarrenty.request.model.Customer;
import com.Inwarrenty.request.model.CustomerAddress;
import com.Inwarrenty.request.model.CustomerProduct;
import com.Inwarrenty.request.model.Problems;
import com.Inwarrenty.servicepackage.CreateJobService;

import io.restassured.module.jsv.JsonSchemaValidator;

public class InwarrentyCreateAPITest {
	 private CreateJobAPIPayload createJobAPIPayload;
	 private CreateJobService createjobservice;
	
	 
	 
	@BeforeMethod(description = "SetUp the Payload for Create API Request and Also setup the Jobservice:")
	public void setup() {

		Customer Customer = new Customer("Bulah", "Peffer", "296-360-0709", "", "Christopher_Willms79@gmail.com", "");
		CustomerAddress CustomerAddress = new CustomerAddress("c 304", "Jupiter", "MG road	", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
		CustomerProduct CustomerProduct = new CustomerProduct(DateTimeUtils.getTimeWithDaysAgo(10), "10558685491181", "10558685491181", "10558685491181", DateTimeUtils.getTimeWithDaysAgo(10), Products.NEXUS_2.getcode(), Models.NEXUS_BLUE.getmodelcode());
		Problems problems = new Problems(Problem.BATTERY_ISSUE.getproblemcode(), "Battery Issue");
		List<Problems> problemArray = new ArrayList<Problems>();
		problemArray.add(problems);
		
		
		 createJobAPIPayload = new CreateJobAPIPayload(ServiceLocation.SERVICE_lOCATION_A.getlocationcode(), Platform.FRONT_DESK.getplatformcode(), WarrentyStatus.IN_WARRENTY.getwarrentcode(),OemId.GOOGLE.getomeidcode() , Customer, CustomerAddress, CustomerProduct, problemArray);
		 createjobservice = new CreateJobService();
		
		
	}
	
	
	@Test(description = "Verifying if the  Create api is able to Create InWarrenty Job ",groups = {"api","regression","smoke"})
	public void createJobApi() throws IOException {
		
		
		
		createjobservice.getCreateJob(Roles.FD, createJobAPIPayload)
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/CreateAPIResponceSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data", Matchers.notNullValue())
		.body("data.job_number", Matchers.startsWith("JOB_"));
		
		
		
		
	}

}
