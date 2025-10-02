package com.Inwarrenty.Test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.DateTimeUtils;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.request.model.CreateJobAPIPayload;
import com.Inwarrenty.request.model.Customer;
import com.Inwarrenty.request.model.CustomerAddress;
import com.Inwarrenty.request.model.CustomerProduct;
import com.Inwarrenty.request.model.problems;

import io.restassured.module.jsv.JsonSchemaValidator;

public class InwarrentyCreateAPITest {
	
	
	@Test
	public void createJobApi() throws IOException {
		
		
		Customer Customer = new Customer("Bulah", "Peffer", "296-360-0709", "", "Christopher_Willms79@gmail.com", "");
		CustomerAddress CustomerAddress = new CustomerAddress("c 304", "Jupiter", "MG road	", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
		CustomerProduct CustomerProduct = new CustomerProduct(DateTimeUtils.getTimeWithDaysAgo(10), "10558685491183", "10558685491183", "10558685491183", DateTimeUtils.getTimeWithDaysAgo(10), 1, 1);
		problems problems = new problems(1, "Battery Issue");
		List<problems> problemArray = new ArrayList<problems>();
		problemArray.add(problems);
		
		
		CreateJobAPIPayload createJobAPIPayload = new CreateJobAPIPayload(0, 2, 1, 1, Customer, CustomerAddress, CustomerProduct, problemArray);
		
		
		
		given() 
		
		.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, createJobAPIPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/CreateAPIResponceSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully."))
		.body("data", Matchers.notNullValue())
		.body("data.job_number", Matchers.startsWith("JOB_"));
		
		
		
		
	}

}
