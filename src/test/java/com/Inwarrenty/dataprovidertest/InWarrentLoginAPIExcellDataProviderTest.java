package com.Inwarrenty.dataprovidertest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.lang.module.ModuleDescriptor.Requires;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.dataproviderbean.UserPOJO;
import com.Inwarrenty.request.model.UserCredentials;
import com.Inwarrenty.servicepackage.AuthService;

import static com.Inwarrenty.Utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class InWarrentLoginAPIExcellDataProviderTest {

	private AuthService authservice;

	@BeforeMethod(description = "Setup the authservice for the test")
	public void setup() {
		authservice = new AuthService();

	}

	@Test(description = "Verifying if login API is Working for FD User!!!", groups = { "api", "Smoke", "regression",
			"datadriven" }, dataProviderClass = com.Inwarrenty.dataprovider.DataProviderUtils.class, dataProvider = "LoginAPIExcellDataProvider")
	public void loginApiTest(UserCredentials userCredentials) throws IOException {

		authservice.login(userCredentials).then().spec(SpecUtils.getResponceSpec()).body("message", equalTo("Success"))
				.body("data.token", notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/LoginResponceSchema.json"));

	}

	@Test(description = "Verifying if login API is Working for FD User!!!", groups = { "api", "Smoke", "regression",
			"datadriven" }, dataProviderClass = com.Inwarrenty.dataprovider.DataProviderUtils.class, dataProvider = "loginAPIExcelDataProviderPoiji")
	public void loginApiTest(UserPOJO user) throws IOException {

		authservice.login(user).then().spec(SpecUtils.getResponceSpec()).body("message", equalTo("Success"))
				.body("data.token", notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/LoginResponceSchema.json"));

	}

}
