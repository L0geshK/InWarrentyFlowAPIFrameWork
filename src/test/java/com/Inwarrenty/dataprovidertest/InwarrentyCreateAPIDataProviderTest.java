package com.Inwarrenty.dataprovidertest;

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
import com.Inwarrenty.Constants.Problems;
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
import com.Inwarrenty.request.model.problems;

import io.restassured.module.jsv.JsonSchemaValidator;

public class InwarrentyCreateAPIDataProviderTest {

	@Test(description = "Verifying if the  Create api is able to Create InWarrenty Job ", groups = { "api",
			"regression",
			"smoke" }, dataProvider = "createJobAPIDataProvider", dataProviderClass = com.Inwarrenty.dataprovider.DataProviderUtils.class)
	public void createJobApi(CreateJobAPIPayload createJobAPIPayload) throws IOException {

		given()

				.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, createJobAPIPayload)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/CreateAPIResponceSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. ")).body("data", Matchers.notNullValue())
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
