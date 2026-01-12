package com.Inwarrenty.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.request.model.UserCredentials;
import com.Inwarrenty.servicepackage.AuthService;

import static  com.Inwarrenty.Utils.ConfigManager.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


@Listeners(com.listener.APITestListener.class)
@Epic("User Management")
@Feature("Authentication")
public class InWarrentLoginAPITest {
	
	private UserCredentials userCredentials;
	private AuthService authservice;
	
	
	@BeforeMethod(description = "Create a Payload For Login API")
	public void setup() {
		 userCredentials = new UserCredentials("iamfd","password");
		 authservice = new AuthService();
		 
	}
	
	
	@Story("Valid User Should Able to Login into the Application")
	@Description("Verify if FD User is able login via api")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verifying if login API is Working for FD User!!!", groups = {"api","Smoke","regression"},retryAnalyzer = com.Inwarrenty.retryAnalyzer.RetryAnalyzer.class)
	public void loginApiTest() throws IOException {
		
		
		
		authservice.login(userCredentials)
		      .then()
		      .spec(SpecUtils.getResponceSpec())
		      .body("message", equalTo("Success"))
		      .body("data.token", notNullValue())
		      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/LoginResponceSchema.json"));
		     

		      
		        
		
	}
	
	
	
	    
	      
	             
	
	
	

}
