package com.Inwarrenty.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.servicepackage.UserDetailsService;

import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listener.APITestListener.class)
public class InWarrentGetUserDetailsTest {
	
	private static  UserDetailsService userdetail;
	
	
	
	@BeforeMethod
	public void setup() {
		userdetail = new UserDetailsService();
	}
	
	
	@Test(description = "Verifying the API get the Correct Responce",groups = {"api","regression","smoke"})
	public void getUserDetails() throws IOException {
		

		userdetail.Userdeatils(Roles.FD)	
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
