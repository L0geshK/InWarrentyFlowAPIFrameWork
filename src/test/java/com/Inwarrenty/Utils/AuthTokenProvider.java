package com.Inwarrenty.Utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.relation.Role;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.request.model.UserCredentials;

public class AuthTokenProvider {
	
	private static Map<Roles,String>tokencache = new ConcurrentHashMap<Roles, String>();
	
	
	
	public static String getToken(Roles role) {	
		
		if(tokencache.containsKey(role)) {
			return tokencache.get(role);
		}
		
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
			    

		tokencache.put(role, token);
		
		return token;
	}

}
