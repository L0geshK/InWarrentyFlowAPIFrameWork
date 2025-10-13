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

import static  com.Inwarrenty.Utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class InWarrentLoginAPIDataProviderTest {
	
	
	
	
	
	
	@Test(description = "Verifying if login API is Working for FD User!!!", 
			groups = {"api","Smoke","regression","datadriven"},
			dataProviderClass = com.Inwarrenty.dataprovider.DataProviderUtils.class,
			dataProvider = "LoginAPITestData")
	public void loginApiTest(UserPOJO UserPOJO) throws IOException  {
		
		
		
		given()
		      .spec(SpecUtils.getRequestSpec(UserPOJO))
		      
		     
		.when()
		      .post("/login")
		.then()
		      .spec(SpecUtils.getResponceSpec())
		      .body("message", equalTo("Success"))
		      .body("data.token", notNullValue())
		      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/LoginResponceSchema.json"));
		     

		      
		        
		
	}
	
	
	
	    
	      
	             
	
	
	

}
