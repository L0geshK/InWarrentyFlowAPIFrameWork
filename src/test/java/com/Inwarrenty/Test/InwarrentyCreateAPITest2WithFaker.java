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
import com.Inwarrenty.Utils.FakerDataGenerator;
import com.Inwarrenty.Utils.SpecUtils;
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
		
		
		
		given() 
		
		.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, createJobAPIPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/CreateAPIResponceSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data", Matchers.notNullValue())
		.body("data.job_number", Matchers.startsWith("JOB_"));
		
		
		
		
	}

}
