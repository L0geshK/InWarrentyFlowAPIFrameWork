package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.Logger;

import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.request.model.UserCredentials;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthService {
	private static final String LOGIN_ENDPOINT = "/login";
	private Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(this.getClass());

	
	@Step("Providing the login request with userCredentials ")
	public Response login(Object userCredentials) {
		log.info("Enter:Make the login request with payload{} :"+((UserCredentials)userCredentials).username(),LOGIN_ENDPOINT);

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpec(userCredentials)).when().post(LOGIN_ENDPOINT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Exit");
		return response;

	}

}
