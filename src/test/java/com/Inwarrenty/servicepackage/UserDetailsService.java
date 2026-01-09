package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserDetailsService {
	private static final String USER_DETAILS_SERVICE = "/userdetails";
	private Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(this.getClass());

	
	@Step("Make the UserDetails API with Role and ENDPOINT")
	public Response Userdeatils(Roles role) {
		log.info("Enter:UserDetails API with Role {} with ENDPOINT {}:",role,USER_DETAILS_SERVICE);

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpecWithAuth(role)).when().get(USER_DETAILS_SERVICE);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		log.info("Exit");
		return response;

	}

}
