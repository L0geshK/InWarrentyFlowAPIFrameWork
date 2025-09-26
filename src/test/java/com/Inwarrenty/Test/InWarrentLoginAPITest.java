package com.Inwarrenty.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Inwarrenty.POJO.UserCredentials;
import com.Inwarrenty.Utils.SpecUtils;

import static  com.Inwarrenty.Utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class InWarrentLoginAPITest {
	
	
	@Test
	public void loginApiTest() throws IOException {
		
		
		UserCredentials userCredentials = new UserCredentials("iamfd","password");
		 
		given()
		      .spec(SpecUtils.getRequestSpec(userCredentials))
		      
		      .log().body()
		      
		.when()
		      .post("/login")
		.then()
		      .spec(SpecUtils.getResponceSpec())
		      .body("message", equalTo("Success"))
		      .body("data.token", notNullValue())
		      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/LoginResponceSchema.json"))
		      .log().body().and().log().status().log().all()
		      .extract().response();

		      
		        
		
	}
	
	
	
	    
	      
	             
	
	
	

}
