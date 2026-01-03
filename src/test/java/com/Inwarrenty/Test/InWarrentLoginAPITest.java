package com.Inwarrenty.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.request.model.UserCredentials;
import com.Inwarrenty.servicepackage.AuthService;

import static  com.Inwarrenty.Utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class InWarrentLoginAPITest {
	
	private UserCredentials userCredentials;
	private AuthService authservice;
	
	
	@BeforeMethod(description = "Create a Payload For Login API")
	public void setup() {
		 userCredentials = new UserCredentials("iamfd","password");
		 authservice = new AuthService();
		 
	}
	
	
	
	@Test(description = "Verifying if login API is Working for FD User!!!", groups = {"api","Smoke","regression"})
	public void loginApiTest() throws IOException {
		
		
		
		authservice.login(userCredentials)
		      .then()
		      .spec(SpecUtils.getResponceSpec())
		      .body("message", equalTo("Success"))
		      .body("data.token", notNullValue())
		      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/LoginResponceSchema.json"));
		     

		      
		        
		
	}
	
	
	
	    
	      
	             
	
	
	

}
