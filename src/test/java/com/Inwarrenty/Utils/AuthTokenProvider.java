package com.Inwarrenty.Utils;

import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.request.model.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class AuthTokenProvider {
	
	
	public static String getToken(Roles role) {
		
		UserCredentials userCredentials = null;
		String token = null;
		
		if(role == Roles.FD) {
			userCredentials = new UserCredentials("iamfd","password");
			
		}else if(role == Roles.SUP) {
			userCredentials = new UserCredentials("iamsup","password");
			
			
		}else if(role == Roles.ENG) {
			userCredentials = new UserCredentials("iameng","password");
			
		}else if(role == Roles.QC) {
			userCredentials = new UserCredentials("iamqc","password");
			
		}
		
		
		try {
			token = given()
					.spec(SpecUtils.getRequestSpec(userCredentials))
				      
				.when()
				      .post("/login")
				.then()
				 .spec(SpecUtils.getResponceSpec())
				      .body("message", equalTo("Success"))
				      .body("data.token", notNullValue())
				      .extract()
				      .body()
				      .jsonPath()
				      .getString("data.token");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			    

		
		return token;
	}

}
