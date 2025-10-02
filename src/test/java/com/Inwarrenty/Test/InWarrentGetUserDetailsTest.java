package com.Inwarrenty.Test;

import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import static com.Inwarrenty.Utils.AuthTokenProvider.getToken;

public class InWarrentGetUserDetailsTest {
	
	
	@Test(description = "Verifying the API get the Correct Responce",groups = {"api","regression","smoke"})
	public void getUserDetails() throws IOException {
		

		given()
		      .spec(SpecUtils.getRequestSpecWithAuth(Roles.FD))
	    .when()
	          .get("/userdetails")
	    .then()
	        .spec(SpecUtils.getResponceSpec())
	          .body("message", equalToIgnoringCase("Success"))
	          .body("data", notNullValue())
	          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/UserDetailResponceSchema.json"))
	          .body("data.role_name", equalToIgnoringCase("FrontDesk"))
	          .body("data.first_name", notNullValue())
	          .body("data.last_name", notNullValue())
	          .body("data.login_id", notNullValue())
	          .body("data.mobile_number", notNullValue())
	          .body("data.email_id", notNullValue())
	          .body("data.password", notNullValue())
	          .body("data.created_at", notNullValue())
	          .body("data.modified_at", notNullValue())
	          .body("data.service_location", notNullValue())
	          .log().body().and().log().all()
	          .extract().response();
	          
		      
		
		
	}

}
