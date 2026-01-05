package com.Inwarrenty.dataprovidertest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.dataproviderbean.UserPOJO;
import com.Inwarrenty.servicepackage.AuthService;

import io.restassured.module.jsv.JsonSchemaValidator;


@Listeners(com.listener.APITestListener.class)
public class InWarrentLoginAPIDataProviderTest {
	private static AuthService authservice;

	@BeforeMethod
	public void setup() {
		authservice = new AuthService();

	}

	@Test(description = "Verifying if login API is Working for FD User!!!", groups = { "api", "Smoke", "regression",
			"datadriven" }, dataProviderClass = com.Inwarrenty.dataprovider.DataProviderUtils.class, dataProvider = "LoginAPITestData")
	public void loginApiTest(UserPOJO UserPOJO) throws IOException {

		authservice.login(UserPOJO)

				.then().spec(SpecUtils.getResponceSpec()).body("message", equalTo("Success"))
				.body("data.token", notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/LoginResponceSchema.json"));

	}

}
