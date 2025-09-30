package com.Inwarrenty.Test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.POJO.CreateJobAPIPayload;
import com.Inwarrenty.POJO.Customer;
import com.Inwarrenty.POJO.CustomerAddress;
import com.Inwarrenty.POJO.CustomerProduct;
import com.Inwarrenty.POJO.problems;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class InwarrentyCreateAPITest {
	
	
	@Test
	public void createJobApi() throws IOException {
		
		
		Customer Customer = new Customer("Bulah", "Peffer", "296-360-0709", "", "Christopher_Willms79@gmail.com", "");
		CustomerAddress CustomerAddress = new CustomerAddress("c 304", "Jupiter", "MG road	", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
		CustomerProduct CustomerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "10558685491183", "10558685491183", "10558685491183", "2025-04-06T18:30:00.000Z", 1, 1);
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
