package com.Inwarrenty.Utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.Logger;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.request.model.UserCredentials;

import io.qameta.allure.Step;

public class AuthTokenProvider {
	
	private static Map<Roles,String>tokencache = new ConcurrentHashMap<Roles, String>();
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(AuthTokenProvider.class);
	
	
	@Step("Getting Auth Token For the Role")
	public static String getToken(Roles role) {	
		log.info(" Enter :Check the token for the role {} is present in the cache",role);
		
		if(tokencache.containsKey(role)) {
			log.info("Token found for the role {}",role);
			return tokencache.get(role);
		}
		log.info(" The token is not found for the role {}",role);
		
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
		log.info("Exit:");
		
		return token;
	}

}
