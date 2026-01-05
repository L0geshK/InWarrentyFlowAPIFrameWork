package com.Inwarrenty.dataprovidertest;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.request.model.CreateJobAPIPayload;
import com.Inwarrenty.servicepackage.CreateJobService;

import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listener.APITestListener.class)
public class InwarrentyCreateAPIExcelDataProviderTestWithPoiji {

	private CreateJobService createjobservice;

	@BeforeMethod
	public void setup() {
		createjobservice = new CreateJobService();
	}

	@Test(description = "Verifying if the  Create api is able to Create InWarrenty Job ", groups = { "api",
			"regression", "smoke",
			"Faker" }, dataProvider = "CreateJobAPIExcelDataProviderPoiji", dataProviderClass = com.Inwarrenty.dataprovider.DataProviderUtils.class)
	public void createJobApi(CreateJobAPIPayload createJobAPIPayload) throws IOException {

		createjobservice.getCreateJob(Roles.FD, createJobAPIPayload).then().spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/CreateAPIResponceSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. ")).body("data", Matchers.notNullValue())
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
